package com.sy.service;

import java.util.List;

import com.sy.domain.Book;
import com.sy.domain.Note;
import com.sy.dto.BookNoteDetailResponse;
import com.sy.dto.CreateNoteRequest;
import com.sy.dto.BookResponse;
import com.sy.dto.CreateBookRequest;

public interface BookService {
    Book findBookById(Long id);
    Note findNoteById(Long id);
    BookNoteDetailResponse getBookNoteDetail(Long id);
    List<BookResponse> getBookList();
    void createNote(Long bookId, CreateNoteRequest request, String writer);
    void deleteNote(Long bookId, Long noteId);
    void createBook(CreateBookRequest request);
} 