package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSearch {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
    private static final String USER = "root";
    private static final String PASS = "Raghu@123";

    public List<String> searchProducts(String category, Double minPrice, Double maxPrice) {
        List<String> products = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT product_name, category, price FROM products WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        if (category != null && !category.trim().isEmpty()) {
            sql.append(" AND category = ?");
            parameters.add(category);
        }

        if (minPrice != null) {
            sql.append(" AND price >= ?");
            parameters.add(minPrice);
        }

        if (maxPrice != null) {
            sql.append(" AND price <= ?");
            parameters.add(maxPrice);
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String productName = rs.getString("product_name");
                String cat = rs.getString("category");
                double price = rs.getDouble("price");
                products.add(productName + " | " + cat + " | $" + price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static void main(String[] args) {
        ProductSearch ps = new ProductSearch();


        System.out.println("Search with all filters:");
        ps.searchProducts("Electronics", 100.0, 500.0).forEach(System.out::println);

        System.out.println("\nSearch with category only:");
        ps.searchProducts("Books", null, null).forEach(System.out::println);

        System.out.println("\nSearch with price range only:");
        ps.searchProducts(null, 50.0, 150.0).forEach(System.out::println);

        System.out.println("\nSearch with no filters (all products):");
        ps.searchProducts(null, null, null).forEach(System.out::println);
    }
}

