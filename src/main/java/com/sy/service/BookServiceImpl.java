package com.sy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sy.domain.Book;
import com.sy.domain.BookRepository;
import com.sy.domain.Note;
import com.sy.domain.NoteRepository;
import com.sy.dto.BookNoteDetailRequest;
import com.sy.dto.BookNoteDetailResponse;

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
    public BookNoteDetailResponse getBookNoteDetail(Long id){
        // TODO: setter 삭제 후 DB 조회 구현

        // TODO: API 검증 후 주석 해제
        // Book book = findBookById(id);
        // Note note = findNoteById(id);

        BookNoteDetailResponse response = new BookNoteDetailResponse();
        
        response.setBookId(id);
        response.setTitle("책 제목");
        response.setAuthor("저자명");
        response.setPublisher("출판사");
        response.setCoverImage("/images/book-cover.jpg");
        response.setDescription("책 설명");

        response.setNoteId(id);
        response.setContent("독서 기록 내용");
        response.setCreatedDate("2025-01-01");

        return response;
    }

    @Override
    public List<Book> getBookList() {
        return bookRepository.findAll();
    }

    @Override
    public void createNote(BookNoteDetailRequest request) {
        // TODO: transactional 적용

        Note note = Note.builder()
            .title(request.getTitle())
            .content(request.getContent())
            .createdDate(request.getCreatedDate())
            .build();
        
        noteRepository.save(note);
    }

} 