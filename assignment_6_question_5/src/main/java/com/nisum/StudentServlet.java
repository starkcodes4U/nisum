package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class StudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jdbc", "root", "Raghu@123");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student10");

            out.println("<h2>Student List</h2>");
            while (rs.next()) {
                out.println("<p>" + rs.getInt("id") + ": " + rs.getString("name") + "</p>");
            }

            con.close();
        } catch (Exception e) {
            out.println("Database error: " + e.getMessage());
        }
    }
}
