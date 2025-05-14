package DAY_3;

import java.util.*;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', category='" + category + "', price=" + price + "}";
    }
}

class ProductComparator implements Comparator<Product> {
    @Override
    public int compare(Product p1, Product p2) {
        // First, compare by category alphabetically
        int categoryComparison = p1.getCategory().compareTo(p2.getCategory());
        if (categoryComparison != 0) {
            return categoryComparison;
        }
        
        // If categories are the same, compare by price in ascending order
        return Double.compare(p1.getPrice(), p2.getPrice());
    }
}

public class CustomProductSorting {

    public static <T> void sortList(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product A", "Electronics", 99.99));
        products.add(new Product("Product B", "Clothing", 49.99));
        products.add(new Product("Product C", "Electronics", 79.99));
        products.add(new Product("Product D", "Clothing", 19.99));
        products.add(new Product("Product E", "Groceries", 9.99));

        ProductComparator productComparator = new ProductComparator();

        sortList(products, productComparator);

        products.forEach(System.out::println);
    }
}
