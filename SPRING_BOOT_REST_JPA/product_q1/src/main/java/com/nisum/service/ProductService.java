package com.nisum.service;

import com.nisum.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long id);
    Product create(Product product);
    Product update(Long id, Product product);
    void delete(Long id);
}
