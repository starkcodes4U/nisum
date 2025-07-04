package com.example.books.controller;

import com.example.books.dto.BookDTO;
import com.example.books.model.Book;
import com.example.books.service.BookService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public Book create(@Valid @RequestBody BookDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<Book> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public Book get(@PathVariable Long id) {
        return service.get(id);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @Valid @RequestBody BookDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
