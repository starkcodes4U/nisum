package com.product.controller;

import com.product.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Product> getAllProducts() {
        return List.of(
                new Product(1L, "Laptop", 80000),
                new Product(2L, "Smartphone", 35000)
        );
    }
}
