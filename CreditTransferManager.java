package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreditTransferManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String USER = "root";
    private static final String PASS = "Raghu@123";

    public void transferCredits(int fromStudentId, int toStudentId, int creditsToTransfer) {
        String debitQuery = "UPDATE students SET credits = credits - ? WHERE studentId = ?";
        String creditQuery = "UPDATE students SET credits = credits + ? WHERE studentId = ?";

        Connection conn = null;
        PreparedStatement debitStmt = null;
        PreparedStatement creditStmt = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false);

            debitStmt = conn.prepareStatement(debitQuery);
            debitStmt.setInt(1, creditsToTransfer);
            debitStmt.setInt(2, fromStudentId);
            int debitRows = debitStmt.executeUpdate();

            creditStmt = conn.prepareStatement(creditQuery);
            creditStmt.setInt(1, creditsToTransfer);
            creditStmt.setInt(2, toStudentId);
            int creditRows = creditStmt.executeUpdate();

            if (debitRows == 1 && creditRows == 1) {
                conn.commit();
                System.out.println("Transfer successful.");
            } else {
                conn.rollback();
                System.out.println("Transfer failed. Transaction rolled back.");
            }

        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
                System.out.println("Error occurred. Transaction rolled back.");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (debitStmt != null) debitStmt.close();
                if (creditStmt != null) creditStmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CreditTransferManager manager = new CreditTransferManager();
        manager.transferCredits(1, 2, 10);
    }
}

