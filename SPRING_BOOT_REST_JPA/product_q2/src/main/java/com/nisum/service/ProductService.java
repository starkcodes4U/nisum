package com.nisum.service;

import com.nisum.entity.Product;
import com.nisum.exception.ResourceNotFoundException;
import com.nisum.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository repository;

    public ProductService() {
        this.repository = new ProductRepository();
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
    }

    public Product addProduct(Product product) {
        return repository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);
        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());
        return repository.save(existing);
    }

    public void deleteProduct(Long id) {
        getProductById(id); // throws if not found
        repository.deleteById(id);
    }
}
