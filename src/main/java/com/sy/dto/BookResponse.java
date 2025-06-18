package com.sy.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookResponse {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String coverImage;
    private String description;
    private List<NoteResponse> notes;
} 