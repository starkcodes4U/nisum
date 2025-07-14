package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void sendOrderEvent(Order order) {
        kafkaTemplate.send("order.created", order);
    }
}
