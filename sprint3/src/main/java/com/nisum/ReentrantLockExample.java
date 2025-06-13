package com.nisum;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private static int counter = 0;
    private static final ReentrantLock lock = new ReentrantLock();

    static class Worker extends Thread {
        private final String name;

        public Worker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                lock.lock(); // Acquire the lock
                try {
                    counter++;
                } finally {
                    lock.unlock(); // Always release the lock in finally
                }
            }
            System.out.println(name + " finished.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Worker("Thread-1");
        Thread t2 = new Worker("Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final Counter Value: " + counter);
    }
}

