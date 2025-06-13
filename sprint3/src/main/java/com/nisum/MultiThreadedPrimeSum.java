package com.nisum;

public class MultiThreadedPrimeSum {

    static class PrimeSumTask extends Thread {
        private final int start;
        private final int end;
        private long partialSum = 0;

        public PrimeSumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public long getPartialSum() {
            return partialSum;
        }

        private boolean isPrime(int num) {
            if (num < 2) return false;
            for (int i = 2; i * i <= num; i++) {
                if (num % i == 0) return false;
            }
            return true;
        }

        @Override
        public void run() {
            for (int i = start; i <= end; i++) {
                if (isPrime(i)) {
                    partialSum += i;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int limit = 100_000; // Change as needed
        int threadCount = 4;

        PrimeSumTask[] threads = new PrimeSumTask[threadCount];
        int chunkSize = limit / threadCount;

        // Create and start threads
        for (int i = 0; i < threadCount; i++) {
            int start = i * chunkSize + 1;
            int end = (i == threadCount - 1) ? limit : (start + chunkSize - 1);

            threads[i] = new PrimeSumTask(start, end);
            threads[i].start();
        }

        // Wait for all threads and collect results
        long totalSum = 0;
        for (PrimeSumTask task : threads) {
            task.join();
            totalSum += task.getPartialSum();
        }

        System.out.println("Sum of prime numbers up to " + limit + " = " + totalSum);
    }
}

