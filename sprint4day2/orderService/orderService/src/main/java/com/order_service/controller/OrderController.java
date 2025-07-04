package com.order_service.controller;

import com.order_service.service.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    MeterRegistry registry;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Endpoint: GET /orders/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getOrderWithUser(@PathVariable Long id) {
        Map<String, Object> response = orderService.getOrderWithUser(id);
        if (response == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(response);
    }

    // Endpoint: GET /orders/user/{id}
    @GetMapping("/user/{id}")
    public Map<String, Object> getUser(@PathVariable int id) {
        registry.counter("user_requests_total").increment();
        // Stub user info for demonstration; replace with real logic as needed
        return Map.of(
                "id", id,
                "name", "Demo User",
                "email", "demo.user@example.com"
        );
    }
}
