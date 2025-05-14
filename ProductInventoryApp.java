package DAY_3;

import java.util.HashMap;
import java.util.Scanner;

public class ProductInventoryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Integer> inventory = new HashMap<>();
        int choice;

        do {
            System.out.println("\n--- Product Inventory System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product Quantity");
            System.out.println("3. Remove Product");
            System.out.println("4. Check Product in Stock");
            System.out.println("5. Display Inventory");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String name = sc.nextLine().toLowerCase();
                    System.out.print("Enter quantity: ");
                    int quantity = sc.nextInt();
                    inventory.put(name, inventory.getOrDefault(name, 0) + quantity);
                    System.out.println("Product added.");
                    break;

                case 2:
                    System.out.print("Enter product name to update: ");
                    String updateName = sc.nextLine().toLowerCase();
                    if (inventory.containsKey(updateName)) {
                        System.out.print("Enter new quantity: ");
                        int newQuantity = sc.nextInt();
                        inventory.put(updateName, newQuantity);
                        System.out.println("Quantity updated.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter product name to remove: ");
                    String removeName = sc.nextLine().toLowerCase();
                    if (inventory.remove(removeName) != null) {
                        System.out.println("Product removed.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter product name to check: ");
                    String checkName = sc.nextLine().toLowerCase();
                    if (inventory.containsKey(checkName)) {
                        System.out.println("Product is in stock with quantity: " + inventory.get(checkName));
                    } else {
                        System.out.println("Product is not in stock.");
                    }
                    break;

                case 5:
                    if (inventory.isEmpty()) {
                        System.out.println("Inventory is empty.");
                    } else {
                        System.out.println("Product Inventory:");
                        for (String key : inventory.keySet()) {
                            System.out.println("Product: " + key + ", Quantity: " + inventory.get(key));
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting inventory system.");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);

        sc.close();
    }
}
