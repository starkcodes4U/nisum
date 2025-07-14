package com.example.ldapauth.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    @GetMapping("/public")
    public String publicPage() {
        return "This is a public page.";
    }

    @GetMapping("/secure")
    public String securePage() {
        return "This is a SECURE page. You are authenticated via LDAP!";
    }
}
