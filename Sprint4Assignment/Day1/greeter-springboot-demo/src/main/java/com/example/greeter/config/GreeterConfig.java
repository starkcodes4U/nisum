package com.example.greeter.config;

import com.example.greeter.service.Greeter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreeterConfig {

    @Bean
    public Greeter greeter() {
        return name -> "Hello, " + name + " from @Bean Config!";
    }
}