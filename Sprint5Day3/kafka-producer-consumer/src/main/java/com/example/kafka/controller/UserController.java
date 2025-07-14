package com.example.kafka.controller;

import com.example.kafka.model.UserEvent;
import com.example.kafka.service.UserEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserEventProducer producer;

    @PostMapping("/event")
    public String postEvent(@RequestBody UserEvent event) {
        producer.sendUserEvent(event);
        return "Event published";
    }
}
