package com.sy.dto;

import lombok.Getter;

@Getter
public class CreateBookRequest {
    private String title;
    private String author;
    private String publisher;
    private String coverImage;
    private String description;
} 