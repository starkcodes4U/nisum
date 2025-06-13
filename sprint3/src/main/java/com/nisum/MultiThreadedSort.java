package com.nisum;

import java.util.Arrays;

public class MultiThreadedSort {

    // Thread to sort a part of the array
    static class SortThread extends Thread {
        private final int[] array;

        public SortThread(int[] array) {
            this.array = array;
        }

        @Override
        public void run() {
            Arrays.sort(array); // built-in efficient sort
        }
    }

    // Function to merge two sorted arrays
    static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            result[k++] = (left[i] < right[j]) ? left[i++] : right[j++];
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        int[] array = {38, 27, 43, 3, 9, 82, 10};

        // Divide array into two halves
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        // Create threads to sort each half
        SortThread t1 = new SortThread(left);
        SortThread t2 = new SortThread(right);

        t1.start();
        t2.start();

        // Wait for both threads to finish
        t1.join();
        t2.join();

        // Merge sorted halves
        int[] sortedArray = merge(left, right);

        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));
    }
}

