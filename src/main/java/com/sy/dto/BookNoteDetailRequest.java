package com.sy.dto;

import lombok.Getter;

/* TODO: DB 구현 후 삭제 */
@Getter
public class BookNoteDetailRequest {
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
