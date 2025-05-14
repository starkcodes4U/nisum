package DAY_3;

import java.util.ArrayList;
import java.util.Scanner;

// Class to represent a product/item
class Item {
    String name;
    double price;
    int quantity;

    Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " - Price: $" + price + ", Quantity: " + quantity;
    }
}

// Class to manage the shopping cart
class ShoppingCart {
    private ArrayList<Item> cartItems;

    ShoppingCart() {
        cartItems = new ArrayList<>();
    }

    public void addItem(String name, double price, int quantity) {
        cartItems.add(new Item(name, price, quantity));
        System.out.println("Item added to cart.");
    }

    public void removeItem(String name) {
        boolean found = false;
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).name.equalsIgnoreCase(name)) {
                cartItems.remove(i);
                System.out.println("Item removed from cart.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Item not found in cart.");
        }
    }

    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Items in Cart:");
            for (Item item : cartItems) {
                System.out.println(item);
            }
        }
    }

    public void calculateTotal() {
        double total = 0;
        for (Item item : cartItems) {
            total += item.price * item.quantity;
        }
        System.out.printf("Total Price: $%.2f\n", total);
    }
}

public class ShoppingCartApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();
        int choice;

        do {
            System.out.println("\n1. Add Item\n2. Remove Item\n3. View Cart\n4. Calculate Total\n5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter item price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter quantity: ");
                    int qty = sc.nextInt();
                    cart.addItem(name, price, qty);
                    break;
                case 2:
                    System.out.print("Enter item name to remove: ");
                    String removeName = sc.nextLine();
                    cart.removeItem(removeName);
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    cart.calculateTotal();
                    break;
                case 5:
                    System.out.println("Thank you! Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}