package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStudentName {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "Raghu@123";

        int studentId = 1;
        String newName = "Jane Smith";

        String sql = "UPDATE student2 SET name = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setInt(2, studentId);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Student's name updated successfully.");
            } else {
                System.out.println("No student found with the given ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
