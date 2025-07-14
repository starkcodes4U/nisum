package com.example.webfluxdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GreetController {
    @GetMapping("/greet")
    public Mono<String> greet() {
        return Mono.just("Hello WebFlux");
    }
}
