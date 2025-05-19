package org.example;

import java.sql.*;

public class ExceptionHandledStudentFetcher {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "Raghu@123";

        String query = "SELECT * FROM student2 WHERE id = ?";

        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(query)
        ) {
            pstmt.setInt(1, 6);  // Change this ID as per your database

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Age: " + rs.getInt("age"));
                    System.out.println("Email: " + rs.getString("email"));
                } else {
                    System.out.println("No student found with that ID.");
                }
            }

        } catch (SQLException e) {
            System.err.println("An error occurred while accessing the database.");
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
        }
    }
}
