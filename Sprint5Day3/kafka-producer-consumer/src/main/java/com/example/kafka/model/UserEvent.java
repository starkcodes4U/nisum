package com.example.kafka.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {
    private String userId;
    private String action;
}
