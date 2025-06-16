package com.nisum.service;

import com.nisum.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAll(String category, Double minPrice, Double maxPrice, String sortBy, String sortDir, int page, int size);
    Product getById(Long id);
    Product save(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}
