package org.example;

import java.sql.*;

public class CallStoredProcedure {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "Raghu@123";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            CallableStatement stmt = conn.prepareCall("{ call getStudentById(?) }");

            stmt.setInt(1, 6);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Email: " + rs.getString("email"));
            }

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

