package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.EmailSender;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;
    private final EmailSender emailSender;

    public UserService(UserRepository userRepository, EmailSender emailSender) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
    }

    public void processUser(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            handleMissingUser(userId);
            throw new RuntimeException("User not found");
        }
        User user = userOpt.get();
        emailSender.send(user, "Welcome to the System");
    }

    public void handleMissingUser(Long userId) {
        System.out.println("Fallback invoked for userId: " + userId);
    }
}
