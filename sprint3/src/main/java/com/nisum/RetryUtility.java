package com.nisum;

import java.util.concurrent.Callable;

public class RetryUtility {

    public static <T> T retry(Callable<T> operation, int maxRetries, long initialDelayMillis) throws Exception {
        int attempts = 0;
        long delay = initialDelayMillis;

        while (true) {
            try {
                return operation.call();  // Attempt the operation
            } catch (Exception ex) {
                attempts++;
                if (attempts > maxRetries) {
                    throw new Exception("Operation failed after " + maxRetries + " retries", ex);
                }
                System.out.println("Attempt " + attempts + " failed. Retrying in " + delay + "ms...");
                Thread.sleep(delay);  // Wait before retrying
                delay *= 2;           // Exponential backoff
            }
        }
    }

    // Simulate a network call (succeeds randomly)
    public static String makeUnstableNetworkCall() throws Exception {
        if (Math.random() < 0.7) {
            throw new Exception("Network call failed");
        }
        return "✅ Network call succeeded!";
    }

    public static void main(String[] args) {
        try {
            String result = retry(() -> makeUnstableNetworkCall(), 5, 1000);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Final failure: " + e.getMessage());
        }
    }
}
