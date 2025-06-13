package com.nisum;

public class MultiThreadedMatrixMultiplication {

    static class Worker extends Thread {
        private final int[][] A, B, result;
        private final int row;

        public Worker(int[][] A, int[][] B, int[][] result, int row) {
            this.A = A;
            this.B = B;
            this.result = result;
            this.row = row;
        }

        @Override
        public void run() {
            int colsB = B[0].length;
            int colsA = A[0].length;

            for (int j = 0; j < colsB; j++) {
                result[row][j] = 0;
                for (int k = 0; k < colsA; k++) {
                    result[row][j] += A[row][k] * B[k][j];
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] A = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] B = {
                {7, 8},
                {9, 10},
                {11, 12}
        };

        int rowsA = A.length;
        int colsB = B[0].length;
        int[][] result = new int[rowsA][colsB];

        Thread[] threads = new Thread[rowsA];

        // Start a thread for each row of the result
        for (int i = 0; i < rowsA; i++) {
            threads[i] = new Worker(A, B, result, i);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (int i = 0; i < rowsA; i++) {
            threads[i].join();
        }

        // Display result
        System.out.println("Result Matrix:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }
}

