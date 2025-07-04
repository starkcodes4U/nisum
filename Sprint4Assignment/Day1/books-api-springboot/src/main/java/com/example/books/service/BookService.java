package com.example.books.service;

import com.example.books.dto.BookDTO;
import com.example.books.model.Book;
import com.example.books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book create(BookDTO dto) {
        return repository.save(new Book(null, dto.getTitle(), dto.getAuthor(), dto.getPrice()));
    }

    public List<Book> list() {
        return repository.findAll();
    }

    public Book get(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Book update(Long id, BookDTO dto) {
        Book book = get(id);
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPrice(dto.getPrice());
        return repository.save(book);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
