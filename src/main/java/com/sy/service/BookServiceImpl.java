package com.sy.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sy.domain.Book;
import com.sy.domain.BookRepository;
import com.sy.domain.Note;
import com.sy.domain.NoteRepository;
import com.sy.dto.BookNoteDetailResponse;
import com.sy.dto.CreateNoteRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final NoteRepository noteRepository;
    private final BookRepository bookRepository;
    
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public Note findNoteById(Long id) {
    
        Note note = noteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Note not found"));

        return note;
    }

    @Override
    public BookNoteDetailResponse getBookNoteDetail(Long id) {
        
        Book book = findBookById(id);
        
        return BookNoteDetailResponse.builder()
                .bookId(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .coverImage(book.getCoverImage())
                .description(book.getDescription())
                .noteId(book.getNotes().get(0).getId())
                .content(book.getNotes().get(0).getContent())
                .createdDate(book.getNotes().get(0).getCreatedDate())
                .build();
    }

    @Override
    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void createNote(Long bookId, CreateNoteRequest request) {
        Book foundBook = bookRepository.findById(bookId) 
            .orElseThrow(() -> new RuntimeException("Book not found"));
        
        Note note = Note.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .book(foundBook)
            .build();
        
        noteRepository.save(note);
    }

} 