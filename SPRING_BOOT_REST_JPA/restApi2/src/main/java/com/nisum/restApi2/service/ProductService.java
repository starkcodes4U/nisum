package com.nisum.restApi2.service;

import com.nisum.restApi2.entity.Product;
import com.nisum.restApi2.repository.ProductRepository;
import com.nisum.restApi2.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository; // ✅ Mark as final

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product product = getById(id);
        product.setName(updated.getName());
        product.setDescription(updated.getDescription());
        product.setPrice(updated.getPrice());
        product.setStockQuantity(updated.getStockQuantity());
        product.setCategory(updated.getCategory());
        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
