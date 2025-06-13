package com.nisum;

import java.io.*;
import java.util.*;

public class FileIntegerReader {

    public static List<Integer> readIntegersFromFile(String filePath) {
        List<Integer> numbers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid number: " + line);
                }
            }
        } catch (IOException e) {  // This already covers EOFException
            System.err.println("Error reading file: " + e.getMessage());
        }

        return numbers;
    }

    public static void main(String[] args) {
        List<Integer> result = readIntegersFromFile("src/numbers.txt");
        System.out.println("Valid numbers found: " + result);
    }
}