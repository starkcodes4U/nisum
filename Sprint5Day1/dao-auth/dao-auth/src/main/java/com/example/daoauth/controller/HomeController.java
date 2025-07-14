// HomeController.java
package com.example.daoauth.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @GetMapping("/public")
    public String publicPage() {
        return "This is a public page.";
    }

    @GetMapping("/secure")
    public String securePage() {
        return "This is a secure page.";
    }
}
