package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    public static java.sql.Connection getMySQLConnection() {
        String url = "jdbc:mysql://localhost:3306/jdbc";  // Replace with your DB name
        String username = "root";                                   // Replace with your DB username
        String password = "Raghu@123";                          // Replace with your DB password

        java.sql.Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful.");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred.");
            e.printStackTrace();
        }

        return conn;
    }

    public static void main(String[] args) {
        java.sql.Connection myConnection = getMySQLConnection();
        if (myConnection != null) {
            System.out.println("Ready to run SQL queries.");
        }
    }
}

