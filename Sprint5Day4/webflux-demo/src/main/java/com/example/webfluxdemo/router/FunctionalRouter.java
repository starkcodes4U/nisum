package com.example.webfluxdemo.router;

import com.example.webfluxdemo.handler.FunctionalHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FunctionalRouter {

    @Bean
    public RouterFunction<ServerResponse> route(FunctionalHandler handler) {
        return RouterFunctions
                .route()
                .GET("/hello", handler::hello)
                .GET("/time", handler::time)
                .build();
    }
}
