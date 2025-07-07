package com.example.demo.util;

import com.example.demo.model.User;

public class EmailSender {
    public void send(User user, String subject) {
        System.out.println("Email sent to: " + user.getEmail() + " | Subject: " + subject);
    }
}