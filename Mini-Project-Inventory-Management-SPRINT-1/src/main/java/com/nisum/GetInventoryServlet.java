package com.nisum;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.*;

import java.io.*;
import java.sql.*;

public class GetInventoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        JSONArray inventoryList = new JSONArray();

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT SKU, Location, Quantity, OrderReservedQty, OrderAllocatedQty FROM ProductInventory")) {

            while (rs.next()) {
                JSONObject item = new JSONObject();
                item.put("SKU", rs.getString("SKU"));
                item.put("Location", rs.getString("Location"));
                item.put("Quantity", rs.getInt("Quantity"));
                item.put("ReservedQty", rs.getInt("OrderReservedQty"));
                item.put("AllocatedQty", rs.getInt("OrderAllocatedQty"));
                inventoryList.put(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        out.print(inventoryList.toString());
    }
}
