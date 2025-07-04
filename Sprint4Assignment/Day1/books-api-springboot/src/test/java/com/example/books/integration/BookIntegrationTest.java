package com.example.books.integration;

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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class BookIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BookRepository bookRepository;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll(); // clean database before each test
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void testCreateBook() throws Exception {
        BookDTO bookDTO = new BookDTO("Clean Architecture", "Robert Martin", 35.99);

        mockMvc.perform(post("/api/books")
                        .header("Authorization", "Bearer demo123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Clean Architecture"))
                .andExpect(jsonPath("$.author").value("Robert Martin"))
                .andExpect(jsonPath("$.price").value(35.99));

        // Assert book is saved in DB
        Optional<Book> saved = bookRepository.findAll().stream().findFirst();
        assertThat(saved).isPresent();
        assertThat(saved.get().getTitle()).isEqualTo("Clean Architecture");
    }
}
