package org.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Q1_ConnectToStudentsDatabase {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "Raghu@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println(" Connected to the database successfully.");

                DatabaseMetaData meta = connection.getMetaData();
                ResultSet tables = meta.getTables(null, null, "students", null);
                if (tables.next()) {
                    System.out.println(" 'students' table found in the database.");
                } else {
                    System.out.println(" 'students' table NOT found in the database.");
                }

                connection.close();
            } else {
                System.out.println("Failed to connect to the database.");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
        }
    }
}
