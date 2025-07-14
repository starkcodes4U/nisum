package com.example.webfluxdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamController {

    @GetMapping(value = "/numbers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Integer> numbers() {
        return Flux.range(1, 10);
    }
}
