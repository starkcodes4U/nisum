// BookServiceImpl.java
package com.example.library.service.impl;

import com.example.library.dto.BookDto;
import com.example.library.factory.BookFactory;
import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookDto save(BookDto bookDto) {
        Book saved = bookRepository.save(BookFactory.createFromDto(bookDto));
        bookDto.setId(saved.getId());
        return bookDto;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> BookDto.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .genre(book.getGenre())
                        .pages(book.getPages())
                        .build())
                .collect(Collectors.toList());
    }
}
