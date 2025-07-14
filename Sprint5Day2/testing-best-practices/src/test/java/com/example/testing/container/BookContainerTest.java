package com.example.testing.container;

import com.example.testing.entity.Book;
import com.example.testing.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookContainerTest {
    @Autowired
    private BookRepository repository;

    @Test
    void testSave() {
        Book book = repository.save(new Book(null, "TC Book", "TC Author"));
        assertThat(book.getId()).isNotNull();
    }
}
