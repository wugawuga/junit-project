package com.wuga.junit_project.web.dto;

import com.wuga.junit_project.domain.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookResDto {
    
    private Long id;
    private String title;
    private String author;

    public BookResDto toDto(Book bookEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.author = bookEntity.getAuthor();
        return this;
    }
}
