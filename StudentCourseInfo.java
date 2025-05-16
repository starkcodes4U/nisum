package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentCourseInfo {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String USER = "root";
    private static final String PASS = "Raghu@123";

    public void getCoursesByStudentId(int studentId) {
        String query = "SELECT c.course_name, c.instructor, e.grade " +
                "FROM students s " +
                "JOIN enrollments e ON s.studentId = e.student_id " +
                "JOIN courses c ON c.id = e.course_id " +
                "WHERE s.studentId = ?";


        try (
                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                String courseName = rs.getString("course_name");
                String instructor = rs.getString("instructor");
                String grade = rs.getString("grade");

                System.out.println("Course: " + courseName +
                        ", Instructor: " + instructor +
                        ", Grade: " + grade);
            }

            if (!found) {
                System.out.println("No courses found for student ID: " + studentId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StudentCourseInfo info = new StudentCourseInfo();
        info.getCoursesByStudentId(1);
    }
}
