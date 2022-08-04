package com.wuga.junit_project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuga.junit_project.domain.Book;
import com.wuga.junit_project.domain.BookRepository;
import com.wuga.junit_project.web.dto.BookRespDto;
import com.wuga.junit_project.web.dto.BookSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceTest {
    
    private final BookRepository bookRepository;

    // 1. 책등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookRespDto 책등록하기_테스트(BookSaveReqDto bookSaveReqDto) {

        Book bookEntity = bookRepository.save(bookSaveReqDto.toEntity());
        return new BookRespDto().toDto(bookEntity);
    }

    // 2. 책목록보기
    @Transactional
    public List<BookRespDto> 책목록보기() {
        return bookRepository.findAll().stream()
            .map(new BookRespDto()::toDto)
            .collect(Collectors.toList());
    }
}
