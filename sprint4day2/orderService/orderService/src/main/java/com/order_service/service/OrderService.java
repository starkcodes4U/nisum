package com.order_service.service;

import com.order_service.model.Order;
import com.example.orderservice.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OrderService {

    private final Map<Long, Order> orderMap = new HashMap<>();
    private long nextOrderId = 3;
    private final RestTemplate restTemplate;
    private static final String USER_SERVICE_URL = "http://localhost:8080/api/users/";

    // Replace with your actual token retrieval logic
    private static final String BEARER_TOKEN = "YOUR_TOKEN_HERE";

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        orderMap.put(1L, new Order(1L, 1L, "Laptop"));
        orderMap.put(2L, new Order(2L, 2L, "Monitor"));
    }

    public Map<String, Object> getOrderWithUser(Long orderId) {
        Order order = orderMap.get(orderId);
        if (order == null) return null;

        User user = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + BEARER_TOKEN);
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<User> userResponse = restTemplate.exchange(
                    USER_SERVICE_URL + order.getUserId(),
                    HttpMethod.GET,
                    entity,
                    User.class
            );
            user = userResponse.getBody();
        } catch (HttpClientErrorException e) {
            // Handle 401 or other errors gracefully
            user = null;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("order", order);
        response.put("user", user);
        return response;
    }

    public Map<String, Object> createOrder(Order order) {
        order.setId(nextOrderId++);
        orderMap.put(order.getId(), order);

        User user = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + BEARER_TOKEN);
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<User> userResponse = restTemplate.exchange(
                    USER_SERVICE_URL + order.getUserId(),
                    HttpMethod.GET,
                    entity,
                    User.class
            );
            user = userResponse.getBody();
        } catch (HttpClientErrorException e) {
            user = null;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("order", order);
        response.put("user", user);
        return response;
    }
}
