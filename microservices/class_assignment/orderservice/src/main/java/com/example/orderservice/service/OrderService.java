package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import java.util.Map;

public interface OrderService {
    Order createOrder(Order order);
    Map<String, Object> getOrderWithUser(Long orderId);
}