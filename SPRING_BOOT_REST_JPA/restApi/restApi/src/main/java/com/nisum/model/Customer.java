package com.nisum.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "customer_db")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "registered_date", nullable = false)
    private LocalDate registeredDate;

    // Default constructor (required by JPA)
    public Customer() {
    }

    // Parameterized constructor (excluding id, as it is auto-generated)
    public Customer(String name, String email, LocalDate registeredDate) {
        this.name = name;
        this.email = email;
        this.registeredDate = registeredDate;
    }

    // Parameterized constructor including id (optional, for completeness)
    public Customer(Long id, String name, String email, LocalDate registeredDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.registeredDate = registeredDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    // Optional: toString(), equals(), and hashCode() methods

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", registeredDate=" + registeredDate +
                '}';
    }
}
