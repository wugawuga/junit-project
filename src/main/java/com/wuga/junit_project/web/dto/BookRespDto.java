package com.wuga.junit_project.web.dto;

import com.wuga.junit_project.domain.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookRespDto {
    
    private Long id;
    private String title;
    private String author;

    public BookRespDto toDto(Book bookEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.author = bookEntity.getAuthor();
        return this;
    }
}
