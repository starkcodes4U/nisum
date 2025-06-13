package com.nisum;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GlobalExceptionLogger {

    public static void main(String[] args) {
        // Set up global exception handler
        Thread.setDefaultUncaughtExceptionHandler(new CustomExceptionHandler());

        // Simulate an unhandled exception
        System.out.println("Program starts...");
        int result = 10 / 0; // This will throw ArithmeticException
        System.out.println("Result: " + result);
    }

    // Custom handler to catch and log uncaught exceptions
    static class CustomExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("error_log.txt", true))) {
                writer.println("Unhandled Exception in Thread: " + t.getName());
                writer.println("Exception Type: " + e.getClass().getName());
                writer.println("Message: " + e.getMessage());
                writer.println("Stack Trace:");
                e.printStackTrace(writer);
                writer.println("--------------------------------------------------");
                System.out.println("An error occurred. Details logged to error_log.txt");
            } catch (IOException ioException) {
                System.err.println("Failed to write to log file: " + ioException.getMessage());
            }
        }
    }
}
