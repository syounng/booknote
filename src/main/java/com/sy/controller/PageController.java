package com.sy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sy.domain.Book;
import com.sy.dto.BookNoteDetailResponse;
import com.sy.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final BookService bookService;
    
    // @GetMapping("/login")
    // public String login() {
    //     return "login";
    // }
    
    // @GetMapping("/join")
    // public String join() {
    //     return "join";
    // }
    
    @GetMapping("/shelf")
    public List<Book> shelf() {
        return bookService.getBookList();
    }

    @GetMapping("/book/{id}")
    public BookNoteDetailResponse bookDetail(@PathVariable Long id) {
        return bookService.getBookNoteDetail(id);
    }
} 