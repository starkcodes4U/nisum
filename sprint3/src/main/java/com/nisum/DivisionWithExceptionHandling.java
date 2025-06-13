package com.nisum;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DivisionWithExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {

            System.out.print("Enter the first number: ");
            double num1 = scanner.nextDouble();


            System.out.print("Enter the second number: ");
            double num2 = scanner.nextDouble();


            if (num2 == 0) {
                throw new ArithmeticException("Division by zero is not allowed \n ArithmaticException occurred");
            }


            double result = num1 / num2;
            System.out.println("Result: " + result);

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter numeric values only.\n InputMismatchException");
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("ended");
        }
    }
}
