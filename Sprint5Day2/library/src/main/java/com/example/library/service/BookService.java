package com.example.library.service;

import com.example.library.dto.BookDto;
import java.util.List;

public interface BookService {
    BookDto save(BookDto bookDto);
    List<BookDto> getAllBooks();
}
