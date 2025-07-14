package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/public")
    public String publicPage() {
        return "This is a public page.";
    }

    @GetMapping("/secure")
    public String securePage() {
        return "This is a SECURE page. Only authenticated users can access this.";
    }
}
