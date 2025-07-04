package com.example.books.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @Positive
    private double price;
}