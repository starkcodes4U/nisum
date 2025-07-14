package com.example.servicea.controller;

import com.example.servicea.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ApiClientController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/call-secure")
    public ResponseEntity<String> callServiceB() {
        String jwt = jwtUtil.generateToken("service-a-client", List.of("USER"));

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(jwt);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        String serviceBUrl = "http://localhost:8081/secure-data";
        ResponseEntity<String> response = restTemplate.exchange(serviceBUrl, HttpMethod.GET, entity, String.class);

        return ResponseEntity.ok("Service B Response: " + response.getBody());
    }
}
