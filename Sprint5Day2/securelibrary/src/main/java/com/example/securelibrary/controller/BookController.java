package com.example.securelibrary.controller;

import com.example.securelibrary.dto.BookDTO;
import com.example.securelibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService svc;

    @GetMapping
    public List<BookDTO> all() {
        return svc.all();
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO dto) {
        return svc.save(dto);
    }
}
