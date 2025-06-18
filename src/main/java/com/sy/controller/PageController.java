package com.sy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sy.dto.BookNoteDetailResponse;
import com.sy.dto.BookResponse;
import com.sy.dto.CreateBookRequest;
import com.sy.dto.CreateNoteRequest;
import com.sy.jwt.JwtUtil;
import com.sy.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PageController {

    private final BookService bookService;
    private final JwtUtil jwtUtil;
    
    @GetMapping("/booklist")
    public List<BookResponse> getBookList() {
        return bookService.getBookList();
    }

    @PostMapping("/booklist")
    public void createBook(@RequestBody CreateBookRequest request) {
        bookService.createBook(request);
    }

    @GetMapping("/book/{id}")
    public BookNoteDetailResponse getBookNoteDetail(@PathVariable Long id) {
        return bookService.getBookNoteDetail(id);
    }

    @PostMapping("/book/{bookId}")
    public void createNote(
        @PathVariable Long bookId, 
        @RequestBody CreateNoteRequest request,
        @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); //"Bearer " 제거
        String writer = jwtUtil.getNameFromToken(token);
        bookService.createNote(bookId, request, writer);
    }

    @DeleteMapping("/book/{bookId}/{noteId}")
    public void deleteNote(@PathVariable Long bookId, @PathVariable Long noteId) {
        bookService.deleteNote(bookId, noteId);
    }
} 