package org.example;

import java.sql.*;
import java.time.LocalDate;

public class Operations {

    static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
    static final String USER = "root";
    static final String PASS = "Raghu@123";

    static class Student {
        int studentId;
        String firstName;
        String lastName;
        String email;
        LocalDate dateOfBirth;

        public Student(int studentId, String firstName, String lastName, String email, LocalDate dateOfBirth) {
            this.studentId = studentId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.dateOfBirth = dateOfBirth;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "studentId=" + studentId +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", dateOfBirth=" + dateOfBirth +
                    '}';
        }
    }

    public static boolean insertStudent(Student student) {
        String query = "INSERT INTO students (studentId, firstName, lastName, email, dateOfBirth) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt.setInt(1, student.studentId);
            stmt.setString(2, student.firstName);
            stmt.setString(3, student.lastName);
            stmt.setString(4, student.email);
            stmt.setDate(5, Date.valueOf(student.dateOfBirth));

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateStudentEmail(int studentId, String newEmail) {
        String query = "UPDATE students SET email = ? WHERE studentId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt.setString(1, newEmail);
            stmt.setInt(2, studentId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteStudent(int studentId) {
        String query = "DELETE FROM students WHERE studentId = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            stmt.setInt(1, studentId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        Student newStudent = new Student(11, "Neha", "Sharma", "neha.sharma@example.com", LocalDate.of(2002, 4, 10));

        System.out.println(insertStudent(newStudent) ? "Insert successful" : "Insert failed");
        System.out.println(updateStudentEmail(11, "neha.updated@example.com") ? "Update successful" : "Update failed");
        System.out.println(deleteStudent(11) ? "Delete successful" : "Delete failed");
    }
}
