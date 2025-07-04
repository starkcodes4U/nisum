package com.user.controller;

import com.user.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public List<User> getAllUsers() {
        return List.of(
                new User(1L, "Madhu", "Madhu@example.com"),
                new User(2L, "Arnav", "bob@example.com")
        );
    }
}
