package com.example.kafka.service;

import com.example.kafka.model.UserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendUserEvent(UserEvent event) {
        kafkaTemplate.send("user-events", event.getUserId(), event.getAction());
    }
}
