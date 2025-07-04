package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.User;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Map<String, Object> getOrderWithUser(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        User user = restTemplate.getForObject("http://localhost:8081/users/" + order.getUserId(), User.class);
        Map<String, Object> response = new HashMap<>();
        response.put("order", order);
        response.put("user", user);
        return response;
    }
}