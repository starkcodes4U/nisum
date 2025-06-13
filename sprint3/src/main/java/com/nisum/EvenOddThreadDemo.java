package com.nisum;

public class EvenOddThreadDemo {

    // Thread to print even numbers from 1 to 10
    static class EvenThread extends Thread {
        public void run() {
            for (int i = 2; i <= 10; i += 2) {
                System.out.println("Even: " + i);
                try {
                    Thread.sleep(500); // pause for half a second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Thread to print odd numbers from 1 to 9
    static class OddThread extends Thread {
        public void run() {
            for (int i = 1; i < 10; i += 2) {
                System.out.println("Odd: " + i);
                try {
                    Thread.sleep(500); // pause for half a second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread evenThread = new EvenThread();
        Thread oddThread = new OddThread();

        evenThread.start(); // start even thread
        oddThread.start();  // start odd thread
    }
}
