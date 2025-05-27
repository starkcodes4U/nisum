package com.nisum;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

            System.out.println("sku=" + sku);
            System.out.println("productIdStr=" + productIdStr);
            System.out.println("categoryIdStr=" + categoryIdStr);
            System.out.println("location=" + location);
            System.out.println("quantityStr=" + quantityStr);

            if (sku == null || sku.isEmpty() ||
                    location == null || location.isEmpty() ||
                    quantityStr == null || quantityStr.isEmpty()) {
                json.put("success", false).put("message", "SKU, Location, and Quantity are required.");
                out.print(json);
                return;
            }

            Integer productId = null;
            Integer categoryId = null;
            int quantity;

            try {
                if (productIdStr != null && !productIdStr.isEmpty())
                    productId = Integer.parseInt(productIdStr);
                if (categoryIdStr != null && !categoryIdStr.isEmpty())
                    categoryId = Integer.parseInt(categoryIdStr);

                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException nfe) {
                json.put("success", false).put("message", "Quantity must be a valid number. ProductID/CategoryID if provided must be numbers.");
                out.print(json);
                return;
            }

            try (Connection conn = DBUtil.getConnection()) {
                if (productId != null) {
                    PreparedStatement checkProduct = null;
                    ResultSet productRs = null;
                    try {
                        System.out.println("Checking if ProductID " + productId + " exists...");
                        checkProduct = conn.prepareStatement("SELECT 1 FROM Product WHERE ProductID = ?");
                        checkProduct.setInt(1, productId);
                        productRs = checkProduct.executeQuery();

                        if (!productRs.next()) {
                            System.out.println("ProductID " + productId + " does not exist in the database");
                            json.put("success", false).put("message", "Product ID " + productId + " doesn't exist in the database. Please add it first.");
                            out.print(json);
                            return;
                        }

                        System.out.println("ProductID " + productId + " exists, proceeding...");
                    } finally {
                        DBUtil.closeQuietly(productRs);
                        DBUtil.closeQuietly(checkProduct);
                    }
                }

                if (categoryId != null) {
                    PreparedStatement checkCategory = null;
                    ResultSet categoryRs = null;
                    try {
                        System.out.println("Checking if CategoryID " + categoryId + " exists...");
                        checkCategory = conn.prepareStatement("SELECT 1 FROM Category WHERE CategoryID = ?");
                        checkCategory.setInt(1, categoryId);
                        categoryRs = checkCategory.executeQuery();

                        if (!categoryRs.next()) {
                            System.out.println("CategoryID " + categoryId + " does not exist in the database");
                            json.put("success", false).put("message", "Category ID " + categoryId + " doesn't exist in the database. Please add it first.");
                            out.print(json);
                            return;
                        }

                        System.out.println("CategoryID " + categoryId + " exists, proceeding...");
                    } finally {
                        DBUtil.closeQuietly(categoryRs);
                        DBUtil.closeQuietly(checkCategory);
                    }
                }

                PreparedStatement stmt = null;
                try {
                    System.out.println("Checking if OrderID 0 exists...");
                    PreparedStatement checkOrder = conn.prepareStatement("SELECT 1 FROM Orders WHERE OrderID = 0");
                    boolean orderZeroExists = checkOrder.executeQuery().next();
                    DBUtil.closeQuietly(checkOrder);

                    String sql = "INSERT INTO ProductInventory (SKU, ProductID, CategoryID, Location, Quantity, OrderID, IsCancelled, OrderAllocatedQty, OrderReservedQty) " +
                            "VALUES (?, ?, ?, ?, ?, ?, FALSE, 0, 0)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, sku);

                    if (productId != null)
                        stmt.setInt(2, productId);
                    else
                        stmt.setNull(2, Types.INTEGER);

                    if (categoryId != null)
                        stmt.setInt(3, categoryId);
                    else
                        stmt.setNull(3, Types.INTEGER);

                    stmt.setString(4, location);
                    stmt.setInt(5, quantity);

                    if (orderZeroExists)
                        stmt.setInt(6, 0);
                    else
                        stmt.setNull(6, Types.INTEGER);

                    System.out.println("Executing SQL insert for SKU: " + sku);
                    int rows = stmt.executeUpdate();
                    System.out.println("SQL insert completed. Rows affected: " + rows);

                    json.put("success", rows > 0).put("message", rows > 0 ? "Inventory added" : "Failed to add inventory");
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
            e.printStackTrace();
            json.put("success", false).put("message", "Error: " + e.getMessage());
        }

        out.print(json);
    }
}
