package com.nisum;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerDemo {

    static class Buffer {
        private final Queue<Integer> queue = new LinkedList<>();
        private final int capacity;

        public Buffer(int capacity) {
            this.capacity = capacity;
        }

        public synchronized void produce(int item) throws InterruptedException {
            while (queue.size() == capacity) {
                System.out.println("Buffer full. Producer waiting...");
                wait(); // Wait if buffer is full
            }
            queue.add(item);
            System.out.println("Produced: " + item);
            notify(); // Notify consumer
        }

        public synchronized int consume() throws InterruptedException {
            while (queue.isEmpty()) {
                System.out.println("Buffer empty. Consumer waiting...");
                wait(); // Wait if buffer is empty
            }
            int item = queue.poll();
            System.out.println("Consumed: " + item);
            notify(); // Notify producer
            return item;
        }
    }

    static class Producer extends Thread {
        private final Buffer buffer;

        public Producer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            int value = 0;
            try {
                while (true) {
                    buffer.produce(value++);
                    Thread.sleep(500); // Simulate delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer extends Thread {
        private final Buffer buffer;

        public Consumer(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    buffer.consume();
                    Thread.sleep(800); // Simulate delay
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // Max 5 items in buffer
        Producer producer = new Producer(buffer);
        Consumer consumer = new Consumer(buffer);

        producer.start();
        consumer.start();
    }
}

