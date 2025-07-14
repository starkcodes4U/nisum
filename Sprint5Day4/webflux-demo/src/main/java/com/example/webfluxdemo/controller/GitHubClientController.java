package com.example.webfluxdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class GitHubClientController {

    private final WebClient webClient = WebClient.create("https://api.github.com");

    @GetMapping("/github/{username}")
    public Mono<String> getUser(@PathVariable String username) {
        return webClient.get()
                .uri("/users/{username}", username)
                .retrieve()
                .bodyToMono(String.class);
    }
}
