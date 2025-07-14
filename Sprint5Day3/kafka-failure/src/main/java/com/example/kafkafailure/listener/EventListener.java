package com.example.kafkafailure.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventListener {

    @KafkaListener(topics = "main-topic", groupId = "event-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {
        System.out.println("Received message: " + message);

        if (message.contains("fail")) {
            throw new RuntimeException("Invalid message content: " + message);
        }

        System.out.println("Processed message successfully: " + message);
    }
}
