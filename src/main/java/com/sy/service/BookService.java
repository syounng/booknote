package com.sy.service;

import com.sy.domain.Book;
import com.sy.domain.Note;
import com.sy.dto.BookNoteDetailResponse;

public interface BookService {
    Book findBookById(Long id);
    Note findNoteById(Long id);
    BookNoteDetailResponse getBookNoteDetail(Long id);
} 