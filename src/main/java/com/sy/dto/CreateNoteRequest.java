package com.sy.dto;

import lombok.Getter;

@Getter
public class CreateNoteRequest {
    private Long bookId;
    private Long noteId;
    private String title;
    private String content;
    private String writer;
}