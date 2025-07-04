package com.example.greeter.service.autoconfig;

import com.example.greeter.service.Greeter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GreeterAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Greeter greeter() {
        return name -> "Hello, " + name + " from AutoConfiguration!";
    }
}