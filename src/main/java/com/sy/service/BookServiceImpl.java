package com.sy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sy.domain.Book;
import com.sy.domain.Note;
import com.sy.dto.BookNoteDetailResponse;

@Service
public class BookServiceImpl implements BookService {
    
    @Override
    public Book findBookById(Long id) {
        // TODO: setter 삭제 후 DB 조회 구현

        Book book = new Book();

        book.setId(id);
        book.setTitle("책 제목");
        book.setAuthor("저자명");
        book.setPublisher("출판사");
        book.setCoverImage("/images/book-cover.jpg");
        book.setDescription("책 설명");

        return book;
    }

    @Override
    public Note findNoteById(Long id) {
        // TODO: setter 삭제 후 DB 조회 구현
    
        Note note = new Note();

        note.setId(id);
        note.setContent("독서 기록 내용");
        note.setCreatedDate("2025-01-01");

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
        // TODO: DB 조회 구현
        List<Book> bookList = new ArrayList<>();

        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("책 제목");
        book1.setAuthor("저자명");
        book1.setPublisher("출판사");
        book1.setCoverImage("/images/book-cover.jpg");

        bookList.add(book1);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("책 제목2");
        book2.setAuthor("저자명2");
        book2.setPublisher("출판사2");
        book2.setCoverImage("/images/book-cover2.jpg");

        bookList.add(book2);

        return bookList;
    }

} 