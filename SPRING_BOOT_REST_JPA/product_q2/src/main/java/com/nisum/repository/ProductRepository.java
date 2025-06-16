package com.nisum.repository;

import com.nisum.entity.Product;
import java.util.*;

public class ProductRepository {
    private final Map<Long, Product> productMap = new HashMap<>();
    private long currentId = 1;

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(currentId++);
        }
        productMap.put(product.getId(), product);
        return product;
    }

    public void deleteById(Long id) {
        productMap.remove(id);
    }
}
