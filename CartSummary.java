package DAY_2;

class Cart {
    String itemName;
    double itemValue;
    String itemId;

    Cart(String itemName, double itemValue, String itemId) {
        if (itemName == null || itemName.isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }
        if (itemValue <= 0) {
            throw new IllegalArgumentException("Item value must be positive.");
        }
        if (itemId == null || itemId.isEmpty()) {
            throw new IllegalArgumentException("Item ID cannot be empty.");
        }
        this.itemName = itemName;
        this.itemValue = itemValue;
        this.itemId = itemId;
    }
}

public class CartSummary {
    public static void main(String[] args) {
        Cart[] cartItems = new Cart[3];
        cartItems[0] = new Cart("Laptop", 75000, "ID001");
        cartItems[1] = new Cart("Mouse", 1200, "ID002");
        cartItems[2] = new Cart("Keyboard", 2200, "ID003");

        int itemCount = cartItems.length;
        double orderTotal = 0;

        System.out.println("Order Summary:");
        for (Cart item : cartItems) {
            System.out.println("Item: " + item.itemName + ", Price: ₹" + item.itemValue + ", ID: " + item.itemId);
            orderTotal += item.itemValue;
        }

        System.out.println("Total Items: " + itemCount);
        System.out.println("Order Total: ₹" + orderTotal);
    }
}
// Output:
// Order Summary:
// Item: Laptop, Price: ₹75000.0, ID: ID001
// Item: Mouse, Price: ₹1200.0, ID: ID002
// Item: Keyboard, Price: ₹2200.0, ID: ID003
// Total Items: 3
// Order Total: ₹78400.0
// The Cart class represents an item in the cart with a name, value, and ID.
// The CartSummary class creates an array of Cart objects, calculates the total number of items,
// and computes the total order value.
// The program demonstrates the use of constructors, arrays, and basic arithmetic operations.
// It also includes error handling for invalid input values in the Cart constructor.
// The program is a simple representation of a shopping cart system.
// The Cart class has a constructor that initializes the item name, value, and ID.
// The constructor checks for valid input values and throws an exception if any are invalid.
