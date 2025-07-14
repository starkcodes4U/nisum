package com.example.shippingservice.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String itemId;
    private int quantity;
}
