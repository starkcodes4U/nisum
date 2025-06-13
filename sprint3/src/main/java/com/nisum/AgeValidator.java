package com.nisum;

// Custom exception
class InvalidAgeException2 extends RuntimeException {
    public InvalidAgeException2(String message) {
        super(message);
    }
}

public class AgeValidator {

    // Method to validate age
    public static void validateAge(int age) {
        if (age <= 0) {
            throw new InvalidAgeException("Age cannot be negative or zero: " + age);
        }
    }

    public static void main(String[] args) {

        int[] testAges = {25, -5, 0, 30, -10};

        for (int age : testAges) {
            try {
                validateAge(age);
                System.out.println("Valid age: " + age);
            } catch (InvalidAgeException e) {
                System.out.println("InvalidAgeException caught: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected exception caught: " + e.getMessage());
            }
        }
    }
}