package DAY_3;

import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String id;
    String name;
    String grade;

    Student(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Grade: " + grade;
    }
}

class StudentManager {
    private ArrayList<Student> studentList;

    StudentManager() {
        studentList = new ArrayList<>();
    }

    public void addStudent(String id, String name, String grade) {
        studentList.add(new Student(id, name, grade));
        System.out.println("Student added successfully.");
    }

    public void removeStudent(String id) {
        boolean found = false;
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).id.equalsIgnoreCase(id)) {
                studentList.remove(i);
                System.out.println("Student removed successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void searchStudent(String id) {
        boolean found = false;
        for (Student student : studentList) {
            if (student.id.equalsIgnoreCase(id)) {
                System.out.println("Student found: " + student);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void displayAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("No students in the list.");
        } else {
            System.out.println("List of Students:");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }
}

public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Student Grade: ");
                    String grade = sc.nextLine();
                    manager.addStudent(id, name, grade);
                    break;

                case 2:
                    System.out.print("Enter Student ID to remove: ");
                    String removeId = sc.nextLine();
                    manager.removeStudent(removeId);
                    break;

                case 3:
                    System.out.print("Enter Student ID to search: ");
                    String searchId = sc.nextLine();
                    manager.searchStudent(searchId);
                    break;

                case 4:
                    manager.displayAllStudents();
                    break;

                case 5:
                    System.out.println("Exiting Student Management System.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
