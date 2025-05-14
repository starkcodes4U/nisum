package DAY_3;

import java.util.*;

class MenuItem {
    String name;
    String description;
    double price;

    MenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - " + description + " ($" + price + ")";
    }
}

public class RestaurantMenuApp {
    static LinkedHashMap<String, HashMap<String, MenuItem>> menu = new LinkedHashMap<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n1. Add Item\n2. Remove Item\n3. Update Item\n4. Display Menu\n5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addItem();
                case 2 -> removeItem();
                case 3 -> updateItem();
                case 4 -> displayMenu();
                case 5 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    static void addItem() {
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        System.out.print("Enter description: ");
        String desc = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        menu.putIfAbsent(category, new HashMap<>());
        menu.get(category).put(name, new MenuItem(name, desc, price));
        System.out.println("Item added.");
    }

    static void removeItem() {
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        if (menu.containsKey(category) && menu.get(category).remove(name) != null) {
            System.out.println("Item removed.");
        } else {
            System.out.println("Item not found.");
        }
    }

    static void updateItem() {
        System.out.print("Enter category: ");
        String category = sc.nextLine();
        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        if (menu.containsKey(category) && menu.get(category).containsKey(name)) {
            System.out.print("Enter new description: ");
            String desc = sc.nextLine();
            System.out.print("Enter new price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            menu.get(category).put(name, new MenuItem(name, desc, price));
            System.out.println("Item updated.");
        } else {
            System.out.println("Item not found.");
        }
    }

    static void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("Menu is empty.");
            return;
        }

        for (Map.Entry<String, HashMap<String, MenuItem>> category : menu.entrySet()) {
            System.out.println("\n== " + category.getKey() + " ==");
            for (MenuItem item : category.getValue().values()) {
                System.out.println(item);
            }
        }
    }
}
