package org.example;

import java.sql.*;

public class TransactionInsertExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "Raghu@123";

        String insertStudentSQL = "INSERT INTO student2 (name, age, email) VALUES (?, ?, ?)";
        String insertAddressSQL = "INSERT INTO student_address (student_id, address, city, state) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);  

            try (
                    PreparedStatement studentStmt = conn.prepareStatement(insertStudentSQL, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement addressStmt = conn.prepareStatement(insertAddressSQL)
            ) {
                studentStmt.setString(1, "Amit Mishra");
                studentStmt.setInt(2, 22);
                studentStmt.setString(3, "amit.mishra@example.com");

                int studentInserted = studentStmt.executeUpdate();

                if (studentInserted == 0) {
                    throw new SQLException("Inserting student failed, no rows affected.");
                }

                ResultSet generatedKeys = studentStmt.getGeneratedKeys();
                int studentId = 0;

                if (generatedKeys.next()) {
                    studentId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve student ID.");
                }

                addressStmt.setInt(1, studentId);
                addressStmt.setString(2, "123 MG Road");
                addressStmt.setString(3, "Bhubaneswar");
                addressStmt.setString(4, "Odisha");

                int addressInserted = addressStmt.executeUpdate();

                if (addressInserted == 0) {
                    throw new SQLException("Inserting address failed.");
                }

                conn.commit();
                System.out.println("Transaction committed successfully.");

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transaction rolled back due to error: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
