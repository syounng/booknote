package com.sy.service;

import java.util.List;

import com.sy.domain.Book;
import com.sy.domain.Note;
import com.sy.dto.BookNoteDetailResponse;

public interface BookService {
    Book findBookById(Long id);
    Note findNoteById(Long id);
    BookNoteDetailResponse getBookNoteDetail(Long id);
    List<Book> getBookList();
} 