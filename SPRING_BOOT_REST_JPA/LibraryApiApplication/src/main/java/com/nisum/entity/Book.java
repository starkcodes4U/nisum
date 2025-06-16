package com.nisum.entity;

import com.nisum.validation.ValidYear;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Book {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 2, message = "Title must have at least 2 characters")
    private String title;

    @NotBlank(message = "Author is required")
    private String author;

    @ValidYear
    private String publishedYear;

    public Book() {}

    public Book(Long id, String title, String author, String publishedYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getPublishedYear() { return publishedYear; }
    public void setPublishedYear(String publishedYear) { this.publishedYear = publishedYear; }
}