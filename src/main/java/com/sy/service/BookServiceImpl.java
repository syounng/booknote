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
import com.sy.dto.BookResponse;
import com.sy.dto.NoteResponse;
import com.sy.dto.CreateBookRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final NoteRepository noteRepository;
    private final BookRepository bookRepository;
    
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));
    }

    @Override
    public Note findNoteById(Long id) {
    
        Note note = noteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("노트를 찾을 수 없습니다."));

        return note;
    }

    @Override
    public BookNoteDetailResponse getBookNoteDetail(Long id) {
        
        try{
            Book foundBook = findBookById(id);
            
            if(foundBook.getNotes().isEmpty()) {
                return BookNoteDetailResponse.builder()
                .bookId(foundBook.getId())
                .title(foundBook.getTitle())
                .author(foundBook.getAuthor())
                .publisher(foundBook.getPublisher())
                .coverImage(foundBook.getCoverImage())
                .description(foundBook.getDescription())
                .build();
            }else {
                return BookNoteDetailResponse.builder()
                        .bookId(foundBook.getId())
                        .title(foundBook.getTitle())
                        .author(foundBook.getAuthor())
                        .publisher(foundBook.getPublisher())
                        .coverImage(foundBook.getCoverImage())
                        .description(foundBook.getDescription())
                        .noteId(foundBook.getNotes().get(0).getId())
                        .content(foundBook.getNotes().get(0).getContent())
                        .createdDate(foundBook.getNotes().get(0).getCreatedDate())
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException("책을 찾을 수 없습니다.");
        }
        
    }

    @Override
    public List<BookResponse> getBookList() {
        List<Book> books = bookRepository.findAll();
        return books.stream().map(book ->
            BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .coverImage(book.getCoverImage())
                .description(book.getDescription())
                .notes(book.getNotes().stream().map(note ->
                    NoteResponse.builder()
                        .id(note.getId())
                        .title(note.getTitle())
                        .content(note.getContent())
                        .createdDate(note.getCreatedDate())
                        .build()
                ).toList())
                .build()
        ).toList();
    }

    @Override
    @Transactional
    public void createNote(Long bookId, CreateNoteRequest request, String writer) {
        Book foundBook = bookRepository.findById(bookId) 
            .orElseThrow(() -> new RuntimeException("책을 찾을 수 없습니다."));
        
        Note note = Note.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .writer(writer)
            .book(foundBook)
            .build();
        
        try {
            noteRepository.save(note);
        } catch (Exception e) {
            throw new RuntimeException("노트를 저장하지 못했습니다.");
        }
    }

    @Override
    public void deleteNote(Long bookId, Long noteId) {

        Note foundNote = noteRepository.findById(noteId)
            .orElseThrow(() -> new RuntimeException("노트를 찾을 수 없습니다."));

        if(foundNote.getBook().getId() != bookId) {
            throw new RuntimeException("책을 찾을 수 없습니다.");
        }

        //양방향 참조로 인한 컬랙션 삭제 처리
        foundNote.getBook().getNotes().remove(foundNote);
        
        try {
            noteRepository.delete(foundNote);
        } catch (Exception e) {
            throw new RuntimeException("노트를 삭제하지 못했습니다.");
        }
    }

    @Override
    @Transactional
    public void createBook(CreateBookRequest request) {
        
        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .coverImage(request.getCoverImage())
                .description(request.getDescription())
                .build();

        try{
            bookRepository.save(book);
        } catch (Exception e) {
            throw new RuntimeException("책을 저장하지 못했습니다.");
        }
    }

} 