package com.example.testing.controller;

import com.example.testing.entity.Book;
import com.example.testing.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book add(@RequestBody Book book) {
        return bookService.save(book);
    }
}
