package com.nisum.repository;

import com.nisum.entity.Product;
import java.util.*;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}