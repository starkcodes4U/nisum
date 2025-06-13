package com.nisum;

public class SharedCounterDemo {

    // Shared counter
    static int counter = 0;

    // Without synchronization
    static void runWithoutSync() throws InterruptedException {
        counter = 0;

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                counter++; // Race condition here
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Without synchronization, counter = " + counter);
    }

    // With synchronization
    static void runWithSync() throws InterruptedException {
        counter = 0;

        Object lock = new Object();

        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (lock) {
                    counter++; // Synchronized access
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("With synchronization, counter = " + counter);
    }

    public static void main(String[] args) throws InterruptedException {
        runWithoutSync();
        runWithSync();
    }
}

