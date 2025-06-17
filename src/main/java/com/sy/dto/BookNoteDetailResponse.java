package com.sy.dto;

import lombok.Getter;
import lombok.Setter;

/* TODO: DB 구현 후 삭제 */
@Getter
@Setter
public class BookNoteDetailResponse {
    private Long bookId;
    private String title;
    private String author;
    private String publisher;
    private String coverImage;
    private String description;

    private Long noteId;
    private String content;
    private String createdDate;
} 
