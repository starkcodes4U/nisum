package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Q2_InsertStudentStatement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "Raghu@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO student1 (id, name, age) VALUES (1, 'Alice', 20)";
            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);

            if (rows > 0) {
                System.out.println(" Student inserted successfully.");
            }

            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println(" Database error.");
            e.printStackTrace();
        }
    }
}
