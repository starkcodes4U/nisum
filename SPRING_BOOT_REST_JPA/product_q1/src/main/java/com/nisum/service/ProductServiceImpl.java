package com.nisum.service;

import com.nisum.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements ProductService {

    private final List<Product> products = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public Product create(Product product) {
        product.setId(idCounter.incrementAndGet());
        products.add(product);
        return product;
    }

    @Override
    public Product update(Long id, Product updated) {
        Product existing = getById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        return existing;
    }

    @Override
    public void delete(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
