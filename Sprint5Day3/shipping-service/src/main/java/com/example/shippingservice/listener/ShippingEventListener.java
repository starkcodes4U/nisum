package com.example.shippingservice.listener;

import com.example.shippingservice.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingEventListener {

    @KafkaListener(topics = "order.created", groupId = "shipping-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderEvent(Order order) {
        System.out.println("ShippingService received: " + order);
        System.out.println("Order ID " + order.getOrderId() + " is being processed for shipping.");
    }
}
