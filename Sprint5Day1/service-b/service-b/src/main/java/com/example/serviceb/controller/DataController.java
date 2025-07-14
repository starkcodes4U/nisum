package com.example.serviceb.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class DataController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint in Service B";
    }

    @GetMapping("/secure-data")
    public String secureEndpoint() {
        return "This is SECURE data from Service B!";
    }
}
