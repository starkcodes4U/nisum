package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertUsingPreparedStatement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "root";
        String password = "Raghu@123";

        String studentName = "John Doe";
        int studentAge = 20;
        String studentEmail = "john.doe@example.com";

        String sql = "INSERT INTO student2 (name, age, email) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentName);
            pstmt.setInt(2, studentAge);
            pstmt.setString(3, studentEmail);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
