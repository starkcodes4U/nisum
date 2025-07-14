package com.example.securelibrary.service;

import com.example.securelibrary.dto.BookDTO;
import com.example.securelibrary.entity.Book;
import com.example.securelibrary.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repo;

    public List<BookDTO> all() {
        return repo.findAll().stream()
                .map(b -> BookDTO.builder()
                        .id(b.getId())
                        .title(b.getTitle())
                        .author(b.getAuthor())
                        .build())
                .toList();
    }

    public BookDTO save(BookDTO dto) {
        Book b = Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .build();
        Book saved = repo.save(b);
        return BookDTO.builder()
                .id(saved.getId())
                .title(saved.getTitle())
                .author(saved.getAuthor())
                .build();
    }
}