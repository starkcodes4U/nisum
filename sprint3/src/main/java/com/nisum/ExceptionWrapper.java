package com.nisum;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.Function;

public class ExceptionWrapper {

    /**
     * Wraps a function that throws IOException into one that throws UncheckedIOException
     */
    public static <T, R> Function<T, R> wrapIOException(IOFunction<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (IOException e) {
                throw new UncheckedIOException("Wrapped IOException", e);
            }
        };
    }

    @FunctionalInterface
    public interface IOFunction<T, R> {
        R apply(T t) throws IOException;
    }

    // Example usage
    public static void main(String[] args) {
        // throws IOException
        IOFunction<String, String> legacyReader = fileName -> {

            if (fileName.isEmpty()) throw new IOException("Empty filename");
            return "Contents of " + fileName;
        };

        // Wrapped version
        Function<String, String> safeReader = wrapIOException(legacyReader);

        try {
            System.out.println(safeReader.apply("data.txt"));  // Works
            System.out.println(safeReader.apply(""));         // Throws UncheckedIOException
        } catch (UncheckedIOException e) {
            System.err.println("Caught wrapped exception: " + e.getMessage());
        }
    }
}