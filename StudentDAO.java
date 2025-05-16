package org.example;

import java.sql.*;
import java.sql.DriverManager;




  class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private String email;

    public Student(int studentId, String firstName, String lastName, String email) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


public class StudentDAO {

    public static Student getStudentById(int studentId) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String username = "root";
        String password = "Raghu@123";

        Student student = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM students WHERE studentId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);



            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");

                student = new Student(studentId, firstName, lastName, email);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
    }

    public static void main(String[] args) {
        Student student = getStudentById(2);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }
}

