package com.nisum.entity;

import com.nisum.validation.ValidCategory;
import jakarta.validation.constraints.*;

public class Product {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, message = "Name must have at least 2 characters")
    private String name;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private Double price;

    @ValidCategory
    private String category;

    public Product() {}

    public Product(Long id, String name, Double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
