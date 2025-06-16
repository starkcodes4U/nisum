package com.nisum.repository;

import com.nisum.entity.Book;

import java.util.*;

public interface BookRepository {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(Book book);
    void deleteById(Long id);
}