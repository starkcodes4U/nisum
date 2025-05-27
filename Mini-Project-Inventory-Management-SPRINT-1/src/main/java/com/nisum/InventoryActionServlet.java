package com.nisum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.*;
import java.sql.*;

public class InventoryActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        try {
            // Get common parameters for all actions
            String action = request.getParameter("action");
            String sku = request.getParameter("sku");
            String quantityStr = request.getParameter("quantity");
            String orderIdStr = request.getParameter("orderId");

            // Debug: print received parameters
            System.out.println("action=" + action);
            System.out.println("sku=" + sku);
            System.out.println("quantityStr=" + quantityStr);
            System.out.println("orderIdStr=" + orderIdStr);

            // Basic validation for required fields
            if (sku == null || sku.isEmpty() || quantityStr == null || quantityStr.isEmpty()) {
                json.put("success", false).put("message", "SKU and Quantity are required for all actions.");
                out.print(json);
                return;
            }

            // Order ID is required for reserve inventory and cancel inventory actions
            if ((action.equals("reserveInventory") || action.equals("cancelInventory")) && (orderIdStr == null || orderIdStr.isEmpty())) {
                json.put("success", false).put("message", "Order ID is required for reserve inventory and cancel inventory actions.");
                out.print(json);
                return;
            }

            int quantity = Integer.parseInt(quantityStr);
            Integer orderId = null;
            if (orderIdStr != null && !orderIdStr.isEmpty()) {
                orderId = Integer.parseInt(orderIdStr);
            }

            // Process based on action type
            switch (action) {
                case "adjustInventory":
                    adjustInventory(sku, quantity, json);
                    break;
                case "cancelInventory":
                    cancelInventory(sku, quantity, orderId, json);
                    break;
                case "reserveInventory":
                    reserveInventory(sku, quantity, orderId, json);
                    break;
                case "allocateInventory":
                    allocateInventory(sku, quantity, orderId, json);
                    break;
                default:
                    json.put("success", false).put("message", "Invalid action specified.");
            }
        } catch (NumberFormatException e) {
            json.put("success", false).put("message", "Quantity and Order ID must be valid numbers.");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("success", false).put("message", "Error processing request: " + e.getMessage());
        }

        out.print(json);
    }

    private void adjustInventory(String sku, int quantity, JSONObject json) {
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            // Check if inventory exists
            checkStmt = conn.prepareStatement("SELECT Quantity FROM ProductInventory WHERE SKU = ?");
            checkStmt.setString(1, sku);
            rs = checkStmt.executeQuery();

            if (!rs.next()) {
                json.put("success", false).put("message", "SKU not found.");
                return;
            }

            // Update the quantity
            updateStmt = conn.prepareStatement(
                    "UPDATE ProductInventory SET Quantity = ? WHERE SKU = ?");
            updateStmt.setInt(1, quantity);
            updateStmt.setString(2, sku);

            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                // Log the adjustment in audit table
                logInventoryChange(conn, sku, "ADJUST", quantity, "Quantity adjusted to " + quantity);
                json.put("success", true).put("message", "Success");
            } else {
                json.put("success", false).put("message", "Operation failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            json.put("success", false).put("message", "Database error.");
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(checkStmt);
            DBUtil.closeQuietly(updateStmt);
            DBUtil.closeQuietly(conn);
        }
    }

    private void cancelInventory(String sku, int quantity, Integer orderId, JSONObject json) {
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement updateStmt = null;
        PreparedStatement orderCheckStmt = null;
        ResultSet rs = null;
        ResultSet orderRs = null;

        try {
            conn = DBUtil.getConnection();

            // Check if inventory exists and get its quantities
            checkStmt = conn.prepareStatement("SELECT Quantity, OrderReservedQty, OrderAllocatedQty FROM ProductInventory WHERE SKU = ?");
            checkStmt.setString(1, sku);
            rs = checkStmt.executeQuery();

            if (!rs.next()) {
                json.put("success", false).put("message", "SKU not found.");
                return;
            }

            int currentQty = rs.getInt("Quantity");
            int reservedQty = rs.getInt("OrderReservedQty");
            int allocatedQty = rs.getInt("OrderAllocatedQty");

            // Check if Order exists - orderId is now mandatory
            orderCheckStmt = conn.prepareStatement("SELECT 1 FROM Orders WHERE OrderID = ?");
            orderCheckStmt.setInt(1, orderId);
            orderRs = orderCheckStmt.executeQuery();

            if (!orderRs.next()) {
                json.put("success", false).put("message", "Invalid order ID.");
                return;
            }

            // First check if there is inventory in reserved for this order
            if (reservedQty >= quantity) {
                // Cancel from reserved inventory and add back to available quantity
                updateStmt = conn.prepareStatement(
                        "UPDATE ProductInventory SET " +
                        "OrderReservedQty = OrderReservedQty - ?, " + // Reduce reserved quantity
                        "Quantity = Quantity + ?, " +                  // Add back to available quantity
                        "IsCancelled = TRUE " +
                        "WHERE SKU = ?");
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, quantity);
                updateStmt.setString(3, sku);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    logInventoryChange(conn, sku, "CANCEL", quantity, "Cancelled reserved inventory for Order #" + orderId);
                    json.put("success", true).put("message", "Success");
                } else {
                    json.put("success", false).put("message", "Operation failed when cancelling reserved inventory.");
                }
            }
            // If no reserved inventory or not enough, check allocated inventory
            else if (allocatedQty >= quantity) {
                // Move from allocated inventory back to available inventory
                updateStmt = conn.prepareStatement(
                        "UPDATE ProductInventory SET " +
                        "OrderAllocatedQty = OrderAllocatedQty - ?, " + // Reduce allocated quantity
                        "Quantity = Quantity + ?, " +                   // Add back to available quantity
                        "IsCancelled = TRUE " +
                        "WHERE SKU = ?");
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, quantity);
                updateStmt.setString(3, sku);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    logInventoryChange(conn, sku, "CANCEL", quantity, "Cancelled allocated inventory for Order #" + orderId);
                    json.put("success", true).put("message", "Success");
                } else {
                    json.put("success", false).put("message", "Operation failed when cancelling allocated inventory.");
                }
            }
            // If both reserved and allocated don't have enough quantity
            else {
                // Try to handle partial quantities from both reserved and allocated
                int remainingQty = quantity;
                boolean success = false;

                // First use whatever is in reserved
                if (reservedQty > 0) {
                    PreparedStatement partialReservedStmt = conn.prepareStatement(
                            "UPDATE ProductInventory SET " +
                            "OrderReservedQty = OrderReservedQty - ?, " +
                            "Quantity = Quantity + ? " +
                            "WHERE SKU = ?");
                    int qtyToCancel = Math.min(reservedQty, remainingQty);
                    partialReservedStmt.setInt(1, qtyToCancel);
                    partialReservedStmt.setInt(2, qtyToCancel);
                    partialReservedStmt.setString(3, sku);

                    int rowsUpdated = partialReservedStmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        logInventoryChange(conn, sku, "CANCEL", qtyToCancel, "Partially cancelled reserved inventory for Order #" + orderId);
                        remainingQty -= qtyToCancel;
                        success = true;
                    }
                    DBUtil.closeQuietly(partialReservedStmt);
                }

                // Then use whatever is in allocated if we still need more
                if (remainingQty > 0 && allocatedQty > 0) {
                    PreparedStatement partialAllocatedStmt = conn.prepareStatement(
                            "UPDATE ProductInventory SET " +
                            "OrderAllocatedQty = OrderAllocatedQty - ?, " +
                            "Quantity = Quantity + ? " +
                            "WHERE SKU = ?");
                    int qtyToCancel = Math.min(allocatedQty, remainingQty);
                    partialAllocatedStmt.setInt(1, qtyToCancel);
                    partialAllocatedStmt.setInt(2, qtyToCancel);
                    partialAllocatedStmt.setString(3, sku);

                    int rowsUpdated = partialAllocatedStmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        logInventoryChange(conn, sku, "CANCEL", qtyToCancel, "Partially cancelled allocated inventory for Order #" + orderId);
                        remainingQty -= qtyToCancel;
                        success = true;
                    }
                    DBUtil.closeQuietly(partialAllocatedStmt);
                }

                if (success) {
                    // Mark as cancelled
                    PreparedStatement cancelStmt = conn.prepareStatement(
                            "UPDATE ProductInventory SET IsCancelled = TRUE WHERE SKU = ?");
                    cancelStmt.setString(1, sku);
                    cancelStmt.executeUpdate();
                    DBUtil.closeQuietly(cancelStmt);

                    if (remainingQty == 0) {
                        json.put("success", true).put("message", "Success: Cancelled inventory for Order #" + orderId);
                    } else {
                        json.put("success", true).put("message", "Partially cancelled inventory for Order #" + orderId +
                                ". Cancelled " + (quantity - remainingQty) + " of " + quantity + " requested units.");
                    }
                } else {
                    json.put("success", false).put("message", "Insufficient quantity in both reserved and allocated inventory.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            json.put("success", false).put("message", "Database error: " + e.getMessage());
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(orderRs);
            DBUtil.closeQuietly(checkStmt);
            DBUtil.closeQuietly(orderCheckStmt);
            DBUtil.closeQuietly(updateStmt);
            DBUtil.closeQuietly(conn);
        }
    }

    private void reserveInventory(String sku, int quantity, Integer orderId, JSONObject json) {
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement orderCheckStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;
        ResultSet orderRs = null;

        try {
            conn = DBUtil.getConnection();

            // Check if inventory exists and has enough quantity, now also check allocated quantity
            checkStmt = conn.prepareStatement("SELECT Quantity, OrderAllocatedQty FROM ProductInventory WHERE SKU = ?");
            checkStmt.setString(1, sku);
            rs = checkStmt.executeQuery();

            if (!rs.next()) {
                json.put("success", false).put("message", "SKU not found.");
                return;
            }

            int currentQty = rs.getInt("Quantity");
            int allocatedQty = rs.getInt("OrderAllocatedQty");

            // Check if Order exists
            orderCheckStmt = conn.prepareStatement("SELECT 1 FROM Orders WHERE OrderID = ?");
            orderCheckStmt.setInt(1, orderId);
            orderRs = orderCheckStmt.executeQuery();

            if (!orderRs.next()) {
                json.put("success", false).put("message", "Invalid order ID.");
                return;
            }

            // First check if there's enough quantity in allocated inventory
            if (allocatedQty >= quantity) {
                // Move from allocated to reserved (deduct from allocated, add to reserved)
                updateStmt = conn.prepareStatement(
                        "UPDATE ProductInventory SET " +
                        "OrderAllocatedQty = OrderAllocatedQty - ?, " +  // Reduce allocated quantity
                        "OrderReservedQty = OrderReservedQty + ? " +     // Increase reserved quantity
                        "WHERE SKU = ?");
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, quantity);
                updateStmt.setString(3, sku);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    // Log the reservation from allocated inventory
                    logInventoryChange(conn, sku, "RESERVE", quantity, "Reserved from allocated inventory for Order #" + orderId);
                    json.put("success", true).put("message", "Success");
                } else {
                    json.put("success", false).put("message", "Operation failed when reserving from allocated inventory.");
                }
            }
            // If not enough in allocated, check if there's enough in available inventory
            else if (currentQty >= quantity) {
                // Update inventory with reserved quantity AND reduce available quantity
                updateStmt = conn.prepareStatement(
                        "UPDATE ProductInventory SET " +
                        "Quantity = Quantity - ?, " +  // Reduce available quantity
                        "OrderReservedQty = OrderReservedQty + ? " +
                        "WHERE SKU = ?");
                updateStmt.setInt(1, quantity);
                updateStmt.setInt(2, quantity);
                updateStmt.setString(3, sku);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    // Log the reservation in audit table
                    logInventoryChange(conn, sku, "RESERVE", quantity, "Reserved from available inventory for Order #" + orderId);
                    json.put("success", true).put("message", "Success");
                } else {
                    json.put("success", false).put("message", "Operation failed when reserving from available inventory.");
                }
            }
            // Try to handle partial quantities from both allocated and available
            else if (allocatedQty + currentQty >= quantity) {
                int remainingQty = quantity;
                boolean success = false;

                // First use whatever is in allocated
                if (allocatedQty > 0) {
                    PreparedStatement partialAllocatedStmt = conn.prepareStatement(
                            "UPDATE ProductInventory SET " +
                            "OrderAllocatedQty = OrderAllocatedQty - ?, " +
                            "OrderReservedQty = OrderReservedQty + ? " +
                            "WHERE SKU = ?");
                    int qtyToReserve = Math.min(allocatedQty, remainingQty);
                    partialAllocatedStmt.setInt(1, qtyToReserve);
                    partialAllocatedStmt.setInt(2, qtyToReserve);
                    partialAllocatedStmt.setString(3, sku);

                    int rowsUpdated = partialAllocatedStmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        logInventoryChange(conn, sku, "RESERVE", qtyToReserve, "Partially reserved from allocated inventory for Order #" + orderId);
                        remainingQty -= qtyToReserve;
                        success = true;
                    }
                    DBUtil.closeQuietly(partialAllocatedStmt);
                }

                // Then use whatever is in available if we still need more
                if (remainingQty > 0 && currentQty > 0) {
                    int qtyToReserve = Math.min(currentQty, remainingQty);
                    PreparedStatement partialAvailableStmt = conn.prepareStatement(
                            "UPDATE ProductInventory SET " +
                            "Quantity = Quantity - ?, " +
                            "OrderReservedQty = OrderReservedQty + ? " +
                            "WHERE SKU = ?");
                    partialAvailableStmt.setInt(1, qtyToReserve);
                    partialAvailableStmt.setInt(2, qtyToReserve);
                    partialAvailableStmt.setString(3, sku);

                    int rowsUpdated = partialAvailableStmt.executeUpdate();
                    if (rowsUpdated > 0) {
                        logInventoryChange(conn, sku, "RESERVE", qtyToReserve, "Partially reserved from available inventory for Order #" + orderId);
                        remainingQty -= qtyToReserve;
                        success = true;
                    }
                    DBUtil.closeQuietly(partialAvailableStmt);
                }

                if (success) {
                    if (remainingQty == 0) {
                        json.put("success", true).put("message", "Success: Reserved inventory for Order #" + orderId);
                    } else {
                        json.put("success", true).put("message", "Partially reserved inventory for Order #" + orderId +
                                ". Reserved " + (quantity - remainingQty) + " of " + quantity + " requested units.");
                    }
                } else {
                    json.put("success", false).put("message", "Failed to reserve inventory.");
                }
            }
            else {
                json.put("success", false).put("message", "Insufficient quantity in both allocated and available inventory.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            json.put("success", false).put("message", "Database error: " + e.getMessage());
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(orderRs);
            DBUtil.closeQuietly(checkStmt);
            DBUtil.closeQuietly(orderCheckStmt);
            DBUtil.closeQuietly(updateStmt);
            DBUtil.closeQuietly(conn);
        }
    }

    private void allocateInventory(String sku, int quantity, Integer orderId, JSONObject json) {
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement orderCheckStmt = null;
        PreparedStatement updateStmt = null;
        ResultSet rs = null;
        ResultSet orderRs = null;

        try {
            conn = DBUtil.getConnection();

            // Check if inventory exists and has enough quantity
            checkStmt = conn.prepareStatement("SELECT Quantity, OrderReservedQty, OrderID FROM ProductInventory WHERE SKU = ?");
            checkStmt.setString(1, sku);
            rs = checkStmt.executeQuery();

            if (!rs.next()) {
                json.put("success", false).put("message", "SKU not found.");
                return;
            }

            int currentQty = rs.getInt("Quantity");
            int reservedQty = rs.getInt("OrderReservedQty");
            Integer currentOrderId = rs.getInt("OrderID");
            boolean sameOrder = (orderId != null && currentOrderId != null && orderId.equals(currentOrderId));

            if (currentQty < quantity) {
                json.put("success", false).put("message", "Insufficient quantity.");
                return;
            }

            // If an order is provided, check if it exists
            if (orderId != null) {
                orderCheckStmt = conn.prepareStatement("SELECT 1 FROM Orders WHERE OrderID = ?");
                orderCheckStmt.setInt(1, orderId);
                orderRs = orderCheckStmt.executeQuery();

                if (!orderRs.next()) {
                    json.put("success", false).put("message", "Invalid order ID.");
                    return;
                }
            }

            // If no orderId is provided, use the default OrderID (0)
            int orderIdToUse = (orderId != null) ? orderId : 0;

            // Update inventory with allocated quantity
            updateStmt = conn.prepareStatement(
                    "UPDATE ProductInventory SET Quantity = Quantity - ?, " +
                    "OrderAllocatedQty = OrderAllocatedQty + ?, " +
                    "OrderID = ? WHERE SKU = ?");
            updateStmt.setInt(1, quantity);
            updateStmt.setInt(2, quantity);
            updateStmt.setInt(3, orderIdToUse);
            updateStmt.setString(4, sku);

            int rowsUpdated = updateStmt.executeUpdate();
            if (rowsUpdated > 0) {
                // Only reduce reserved quantity if this is not an allocation for the same order
                // that already has reserved items
                if (reservedQty > 0 && !sameOrder) {
                    PreparedStatement reduceReserveStmt = conn.prepareStatement(
                            "UPDATE ProductInventory SET OrderReservedQty = GREATEST(0, OrderReservedQty - ?) WHERE SKU = ?");
                    reduceReserveStmt.setInt(1, quantity);
                    reduceReserveStmt.setString(2, sku);
                    reduceReserveStmt.executeUpdate();
                    DBUtil.closeQuietly(reduceReserveStmt);
                }

                // Log the allocation in audit table
                String reason = (orderId != null) ? "Allocated for Order #" + orderId : "Allocated";
                logInventoryChange(conn, sku, "ALLOCATE", quantity, reason);
                json.put("success", true).put("message", "Success");
            } else {
                json.put("success", false).put("message", "Operation failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            json.put("success", false).put("message", "Database error.");
        } finally {
            DBUtil.closeQuietly(rs);
            DBUtil.closeQuietly(orderRs);
            DBUtil.closeQuietly(checkStmt);
            DBUtil.closeQuietly(orderCheckStmt);
            DBUtil.closeQuietly(updateStmt);
            DBUtil.closeQuietly(conn);
        }
    }

    private void logInventoryChange(Connection conn, String sku, String changeType, int quantityChanged, String reason) {
        PreparedStatement logStmt = null;

        try {
            // Get next available AuditID - assuming auto-increment is not available
            PreparedStatement maxIdStmt = conn.prepareStatement("SELECT MAX(AuditID) + 1 AS NextID FROM InventoryAuditLog");
            ResultSet idRs = maxIdStmt.executeQuery();
            int nextId = 1; // Default to 1 if table is empty
            if (idRs.next()) {
                Integer id = idRs.getInt("NextID");
                if (id != null) {
                    nextId = id;
                }
            }
            DBUtil.closeQuietly(idRs);
            DBUtil.closeQuietly(maxIdStmt);

            // Insert audit record
            logStmt = conn.prepareStatement(
                    "INSERT INTO InventoryAuditLog (AuditID, SKU, ChangeType, QuantityChanged, Timestamp, Reason) " +
                    "VALUES (?, ?, ?, ?, NOW(), ?)");
            logStmt.setInt(1, nextId);
            logStmt.setString(2, sku);
            logStmt.setString(3, changeType);
            logStmt.setInt(4, quantityChanged);
            logStmt.setString(5, reason);

            logStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Warning: Failed to log inventory change: " + e.getMessage());
            e.printStackTrace();
            // Don't throw - this is a non-critical operation
        } finally {
            DBUtil.closeQuietly(logStmt);
        }
    }
}
