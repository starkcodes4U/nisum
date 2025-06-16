package com.nisum.service;

import com.nisum.entity.Product;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public List<Product> getAll() {
        return products;
    }

    public Product getById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Product not found with ID: " + id));
    }

    public Product save(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    public Product update(Long id, Product newProduct) {
        Product existing = getById(id);
        existing.setName(newProduct.getName());
        existing.setPrice(newProduct.getPrice());
        existing.setCategory(newProduct.getCategory());
        return existing;
    }

    public void delete(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}