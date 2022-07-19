package com.wuga.junit_project.web.dto;

import com.wuga.junit_project.domain.Book;

import lombok.Setter;

@Setter
public class BookSaveReqDto {
    
    private String title;
    private String author;

    public Book toEntity() {
        return Book.builder()
            .title(title)
            .author(author)
            .build();
    }
}
