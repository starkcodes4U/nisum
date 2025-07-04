package com.example.greeter.service;

import org.springframework.stereotype.Component;

@Component
public class GreeterImpl implements Greeter {
    @Override
    public String greet(String name) {
        return "Hello, " + name + " from @Component!";
    }
}