package com.cloudSpringDemo.SpringDemo;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        String timestamp = LocalDateTime.now().toString();
        System.out.println("[Gateway LOG] Path: " + path + " | Time: " + timestamp);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // ensure this filter runs first
    }
}

