package com.sy.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

/* TODO: DB 구현 후 삭제 */
@Getter
@Builder
public class BookNoteDetailResponse {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private String coverImage;
    private String description;

    private Long noteId;
    private String content;
    private LocalDateTime createdDate;
} 
