package com.example.books.controller;

import com.example.books.dto.BookDTO;
import com.example.books.model.Book;
import com.example.books.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class BookControllerTest {


    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        bookRepository.deleteAll(); // Clean DB before each test
    }

    @Test
    void shouldCreateBook() throws Exception {
        BookDTO dto = new BookDTO("Effective Java", "Joshua Bloch", 45.0);

        mockMvc.perform(post("/api/books")
                        .header("Authorization", "Bearer dummy-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java"));

        List<Book> allBooks = bookRepository.findAll();
        assertThat(allBooks).hasSize(1);
        assertThat(allBooks.get(0).getAuthor()).isEqualTo("Joshua Bloch");
    }

    @Test
    void shouldReturnAllBooks() throws Exception {
        bookRepository.save(new Book(null, "Book A", "Author A", 10.0));
        bookRepository.save(new Book(null, "Book B", "Author B", 20.0));

        mockMvc.perform(get("/api/books")
                        .header("Authorization", "Bearer dummy-token"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        Book book = bookRepository.save(new Book(null, "Old Title", "Old Author", 10.0));
        BookDTO updated = new BookDTO("New Title", "New Author", 25.0);

        mockMvc.perform(put("/api/books/" + book.getId())
                        .header("Authorization", "Bearer dummy-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Title"));

        Book fromDb = bookRepository.findById(book.getId()).get();
        assertThat(fromDb.getTitle()).isEqualTo("New Title");
    }

    @Test
    void shouldDeleteBook() throws Exception {
        Book book = bookRepository.save(new Book(null, "To be deleted", "Author", 15.0));

        mockMvc.perform(delete("/api/books/" + book.getId())
                        .header("Authorization", "Bearer dummy-token"))
                .andExpect(status().isOk());

        assertThat(bookRepository.existsById(book.getId())).isFalse();
    }
}

