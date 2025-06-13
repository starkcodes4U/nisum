package com.nisum;

import java.io.*;

class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class AgeValidator2 {

    public static void validateAge(int age) {
        if (age <= 0) {
            throw new InvalidAgeException("Age cannot be negative or zero: " + age);
        }
    }

    public static void main(String[] args) {
        // File path for storing validation results
        String filePath = "age_validation_results.txt";

        // Try-with-resources automatically closes the writer
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            int[] testAges = {25, -5, 0, 30, -10};

            for (int age : testAges) {
                try {
                    validateAge(age);
                    writer.println("Valid age: " + age);
                } catch (InvalidAgeException e) {
                    writer.println("InvalidAgeException caught: " + e.getMessage());
                } catch (Exception e) {
                    writer.println("Unexpected exception caught: " + e.getMessage());
                }
            }

            System.out.println("Age validation results written to " + filePath);

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
