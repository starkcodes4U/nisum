package com.example.library.factory;

import com.example.library.dto.BookDto;
import com.example.library.model.Book;

public class BookFactory {
    public static Book createFromDto(BookDto dto) {
        return Book.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .genre(dto.getGenre())
                .pages(dto.getPages())
                .build();
    }
}
