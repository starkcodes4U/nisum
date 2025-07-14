package com.example.library;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookRepository repository;

    @Test
    void shouldSaveBook() {
        Book book = new Book(null, "Test", "Author", "Genre", 123);
        Book saved = repository.save(book);
        assertThat(saved.getId()).isNotNull();
    }
}
