package com.nisum;

public class Employee {
    private String name;
    private Address address;

    public Employee(String name, Address address) {
        System.out.println("Constructor Injection successful for Employee");
        this.name = name;
        this.address = address;
    }

    public void show() {
        System.out.println("Employee Name: " + name);
        System.out.print("Address: ");
        address.display();
    }
}
