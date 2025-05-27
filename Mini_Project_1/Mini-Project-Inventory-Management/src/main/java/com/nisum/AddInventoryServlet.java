package com.nisum;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.json.JSONObject;

import java.io.*;
import java.sql.*;

public class AddInventoryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        try {
            String sku = request.getParameter("sku");
            String productIdStr = request.getParameter("productId");
            String categoryIdStr = request.getParameter("categoryId");
            String location = request.getParameter("location");
            String quantityStr = request.getParameter("quantity");

            // Debug: print received parameters
            System.out.println("sku=" + sku);
            System.out.println("productIdStr=" + productIdStr);
            System.out.println("categoryIdStr=" + categoryIdStr);
            System.out.println("location=" + location);
            System.out.println("quantityStr=" + quantityStr);

            // Null/empty checks
            if (sku == null || sku.isEmpty() ||
                productIdStr == null || productIdStr.isEmpty() ||
                categoryIdStr == null || categoryIdStr.isEmpty() ||
                location == null || location.isEmpty() ||
                quantityStr == null || quantityStr.isEmpty()) {
                json.put("success", false).put("message", "All fields are required and must not be empty.");
                out.print(json); return;
            }

            int productId, categoryId, quantity;
            try {
                productId = Integer.parseInt(productIdStr);
                categoryId = Integer.parseInt(categoryIdStr);
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException nfe) {
                json.put("success", false).put("message", "Product ID, Category ID, and Quantity must be valid numbers.");
                out.print(json); return;
            }

            try (Connection conn = DBUtil.getConnection()) {
                // Check if Product exists first
                PreparedStatement checkProduct = null;
                ResultSet productRs = null;
                try {
                    System.out.println("Checking if ProductID " + productId + " exists...");
                    checkProduct = conn.prepareStatement("SELECT 1 FROM Product WHERE ProductID = ?");
                    checkProduct.setInt(1, productId);
                    productRs = checkProduct.executeQuery();

                    if (!productRs.next()) {
                        // Product doesn't exist - helpful error message
                        System.out.println("ProductID " + productId + " does not exist in the database");
                        json.put("success", false).put("message", "Product ID " + productId + " doesn't exist in the database. Please add it first.");
                        out.print(json);
                        return;
                    }

                    System.out.println("ProductID " + productId + " exists, proceeding with insert");
                } finally {
                    DBUtil.closeQuietly(productRs);
                    DBUtil.closeQuietly(checkProduct);
                }

                // Now check if Category exists
                PreparedStatement checkCategory = null;
                ResultSet categoryRs = null;
                try {
                    System.out.println("Checking if CategoryID " + categoryId + " exists...");
                    checkCategory = conn.prepareStatement("SELECT 1 FROM Category WHERE CategoryID = ?");
                    checkCategory.setInt(1, categoryId);
                    categoryRs = checkCategory.executeQuery();

                    if (!categoryRs.next()) {
                        // Category doesn't exist - helpful error message
                        System.out.println("CategoryID " + categoryId + " does not exist in the database");
                        json.put("success", false).put("message", "Category ID " + categoryId + " doesn't exist in the database. Please add it first.");
                        out.print(json);
                        return;
                    }

                    System.out.println("CategoryID " + categoryId + " exists, proceeding with insert");
                } finally {
                    DBUtil.closeQuietly(categoryRs);
                    DBUtil.closeQuietly(checkCategory);
                }

                PreparedStatement stmt = null;
                try {
                    // Check if OrderID 0 exists in Orders table
                    System.out.println("Checking if OrderID 0 exists or if we need to modify our SQL...");
                    PreparedStatement checkOrder = conn.prepareStatement("SELECT 1 FROM Orders WHERE OrderID = 0");
                    boolean orderZeroExists = checkOrder.executeQuery().next();
                    DBUtil.closeQuietly(checkOrder);

                    // Modify our SQL based on whether OrderID 0 exists
                    String sql;
                    if (orderZeroExists) {
                        sql = "INSERT INTO ProductInventory (SKU, ProductID, CategoryID, Location, Quantity, OrderID, IsCancelled, OrderAllocatedQty, OrderReservedQty) " +
                              "VALUES (?, ?, ?, ?, ?, 0, FALSE, 0, 0)";
                    } else {
                        // If OrderID 0 doesn't exist, we need to make OrderID NULL
                        sql = "INSERT INTO ProductInventory (SKU, ProductID, CategoryID, Location, Quantity, OrderID, IsCancelled, OrderAllocatedQty, OrderReservedQty) " +
                              "VALUES (?, ?, ?, ?, ?, NULL, FALSE, 0, 0)";
                    }

                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, sku);
                    stmt.setInt(2, productId);
                    stmt.setInt(3, categoryId);
                    stmt.setString(4, location);
                    stmt.setInt(5, quantity);

                    System.out.println("Executing SQL insert for SKU: " + sku);
                    int rows = stmt.executeUpdate();
                    System.out.println("SQL insert completed. Rows affected: " + rows);

                    json.put("success", rows > 0).put("message", rows > 0 ? "Inventory added" : "Failed to add");
                } finally {
                    DBUtil.closeQuietly(stmt);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                json.put("success", false).put("message", "Database error: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                json.put("success", false).put("message", "Error: " + e.getMessage());
            }
        } catch (Exception e) {
            json.put("success", false).put("message", "Error: " + e.getMessage());
        }
        out.print(json);
    }
}
