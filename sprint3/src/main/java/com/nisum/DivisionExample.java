package com.nisum;

public class DivisionExample {

    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return dividend / divisor;
    }

    public static void main(String[] args) {

        int[] testDividends = {10, 15, 20, 5, 12};
        int[] testDivisors = {2, 0, 4, 5, 0};

        for (int i = 0; i < testDividends.length; i++) {
            try {
                int result = divide(testDividends[i], testDivisors[i]);
                System.out.println(testDividends[i] + " / " + testDivisors[i] + " = " + result);
            } catch (ArithmeticException e) {
                System.out.println("Error dividing " + testDividends[i] + " by " + testDivisors[i] + ": " + e.getMessage());
            }
        }
    }
}