package com.example.webfluxdemo.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Component
public class FunctionalHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().bodyValue("Hello Functional");
    }

    public Mono<ServerResponse> time(ServerRequest request) {
        return ServerResponse.ok().bodyValue(Instant.now().toString());
    }
}
