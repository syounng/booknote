package com.sy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sy.domain.Book;
import com.sy.dto.BookNoteDetailResponse;
import com.sy.dto.CreateNoteRequest;
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
    
    @GetMapping("/booklist")
    public List<Book> getBookList() {
        return bookService.getBookList();
    }

    @GetMapping("/book/{id}")
    public BookNoteDetailResponse getBookNoteDetail(@PathVariable Long id) {
        return bookService.getBookNoteDetail(id);
    }

    @PostMapping("/book/{id}")
    public void createNote(@PathVariable Long id, @RequestBody CreateNoteRequest request) {
        bookService.createNote(id, request);
    }

    @DeleteMapping("/book/{bookId}/{noteId}")
    public void deleteNote(@PathVariable Long bookId, @PathVariable Long noteId) {
        bookService.deleteNote(bookId, noteId);
    }
} 