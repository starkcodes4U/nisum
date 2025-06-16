package com.nisum.service;

import com.nisum.entity.Book;
import com.nisum.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Book> getAll(String author, String year, int page, int size) {
        return books.stream()
                .filter(b -> author == null || b.getAuthor().equalsIgnoreCase(author))
                .filter(b -> year == null || b.getPublishedYear().equals(year))
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    @Override
    public Book getById(Long id) {
        return books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Book not found with ID: " + id));
    }

    @Override
    public Book save(Book book) {
        book.setId(nextId++);
        books.add(book);
        return book;
    }

    @Override
    public Book update(Long id, Book newBook) {
        Book existing = getById(id);
        existing.setTitle(newBook.getTitle());
        existing.setAuthor(newBook.getAuthor());
        existing.setPublishedYear(newBook.getPublishedYear());
        return existing;
    }

    @Override
    public void delete(Long id) {
        books.removeIf(b -> b.getId().equals(id));
    }
}