package com.nisum.jagannath.controller;

import com.nisum.jagannath.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    // In-memory store
    private static final Map<Integer, Product> store = new HashMap<>();
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    static {
        // Pre-populate with a couple of products
        store.put(1, new Product(1, "Laptop", 999.99));
        store.put(2, new Product(2, "Phone", 499.49));
        idCounter.set(3);
    }

    // 1) GET /api/products → list all
    @GetMapping
    public Collection<Product> getAll() {
        return store.values();
    }

    // 2) GET /api/products/{id} → single product
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") int id) {
        Product p = store.get(id);
        if (p == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    // 3) POST /api/products (JSON payload) → create
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product prod) {
        int newId = idCounter.getAndIncrement();
        prod.setId(newId);
        store.put(newId, prod);
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    // 4) PUT /api/products/{id} → update
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id,
                                          @RequestBody Product prod) {
        Product existing = store.get(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existing.setName(prod.getName());
        existing.setPrice(prod.getPrice());
        return new ResponseEntity<>(existing, HttpStatus.OK);
    }

    // 5) DELETE /api/products/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        Product removed = store.remove(id);
        if (removed == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
