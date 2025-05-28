package com.nisum;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private int empId;
    private String firstName;
    private String lastName;
    private String department;

    // Constructor
    public Employee(int empId, String firstName, String lastName, String department) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    // Getters
    public int getEmpId() {
        return empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDepartment() {
        return department;
    }

    // For display
    @Override
    public String toString() {
        return empId + " - " + firstName + " " + lastName + " (" + department + ")";
    }
}

public class IndianEmployeeOperations {

    // 4. Pad store ID
    public static String padStoreId(String storeId) {
        return String.format("%04d", Integer.parseInt(storeId));
    }

    // 5. Employees not in given department
    public static List<Employee> filterByDepartment(List<Employee> employees, String department) {
        return employees.stream()
                .filter(emp -> !emp.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    // 6. Sort by first name
    public static List<Employee> sortByFirstName(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getFirstName))
                .collect(Collectors.toList());
    }

    // 7. Employee with highest empId
    public static Optional<Employee> getEmployeeWithHighestId(List<Employee> employees) {
        return employees.stream()
                .max(Comparator.comparingInt(Employee::getEmpId));
    }

    // Main method
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee(1003, "Amit", "Sharma", "IT"),
                new Employee(1007, "Priya", "Reddy", "Finance"),
                new Employee(1012, "Ravi", "Kumar", "Operations"),
                new Employee(1021, "Neha", "Patel", "HR"),
                new Employee(1001, "Arjun", "Verma", "IT")
        );

        // 4. Pad store ID
        String paddedStoreId = padStoreId("83");
        System.out.println("Padded Store ID: " + paddedStoreId);

        // 5. Employees NOT in IT
        List<Employee> nonIT = filterByDepartment(employees, "IT");
        System.out.println("\nEmployees NOT in IT Department:");
        nonIT.forEach(System.out::println);

        // 6. Sorted by First Name
        List<Employee> sortedByName = sortByFirstName(employees);
        System.out.println("\nEmployees Sorted by First Name:");
        sortedByName.forEach(System.out::println);

        // 7. Highest empId
        Optional<Employee> topEmp = getEmployeeWithHighestId(employees);
        System.out.println("\nEmployee with Highest empId:");
        topEmp.ifPresent(System.out::println);
    }
}

