package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderEventProducer producer;

    @PostMapping
    public String createOrder(@RequestBody Order order) {
        producer.sendOrderEvent(order);
        return "Order event sent to Kafka";
    }
}
