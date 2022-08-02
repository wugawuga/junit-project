package com.wuga.junit_project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuga.junit_project.domain.Book;
import com.wuga.junit_project.domain.BookRepository;
import com.wuga.junit_project.web.dto.BookResDto;
import com.wuga.junit_project.web.dto.BookSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceTest {
    
    private final BookRepository bookRepository;

    // 1. 책등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResDto 책등록하기_테스트(BookSaveReqDto bookSaveReqDto) {

        Book bookEntity = bookRepository.save(bookSaveReqDto.toEntity());
        return new BookResDto().toDto(bookEntity);
    }
}
