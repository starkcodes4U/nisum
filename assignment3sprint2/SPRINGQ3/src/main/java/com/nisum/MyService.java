package com.nisum;

import org.springframework.stereotype.Component;

@Component
public class MyService {
    public void performTask() {
        System.out.println("Performing a task...");
        // Simulate some work
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
