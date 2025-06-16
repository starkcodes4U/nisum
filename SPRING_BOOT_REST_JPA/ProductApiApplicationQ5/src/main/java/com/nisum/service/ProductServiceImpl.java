package com.nisum.service;

import com.nisum.entity.Product;
import com.nisum.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Product> getAll(String category, Double minPrice, Double maxPrice, String sortBy, String sortDir, int page, int size) {
        return products.stream()
                .filter(p -> category == null || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> minPrice == null || p.getPrice() >= minPrice)
                .filter(p -> maxPrice == null || p.getPrice() <= maxPrice)
                .sorted((p1, p2) -> {
                    if ("desc".equalsIgnoreCase(sortDir)) {
                        return Double.compare(p2.getPrice(), p1.getPrice());
                    }
                    return Double.compare(p1.getPrice(), p2.getPrice());
                })
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    @Override
    public Product getById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
    }

    @Override
    public Product save(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    @Override
    public Product update(Long id, Product newProduct) {
        Product existing = getById(id);
        existing.setName(newProduct.getName());
        existing.setDescription(newProduct.getDescription());
        existing.setPrice(newProduct.getPrice());
        existing.setStockQuantity(newProduct.getStockQuantity());
        existing.setCategory(newProduct.getCategory());
        return existing;
    }

    @Override
    public void delete(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
