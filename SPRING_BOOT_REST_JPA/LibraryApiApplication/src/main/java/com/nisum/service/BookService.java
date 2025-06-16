package com.nisum.service;

import com.nisum.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAll(String author, String year, int page, int size);
    Book getById(Long id);
    Book save(Book book);
    Book update(Long id, Book book);
    void delete(Long id);
}