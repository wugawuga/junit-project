package com.wuga.junit_project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wuga.junit_project.domain.BookRepository;
import com.wuga.junit_project.util.MailSenderSutb;
import com.wuga.junit_project.web.dto.BookRespDto;
import com.wuga.junit_project.web.dto.BookSaveReqDto;

@DataJpaTest
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;
    
    // 문제점: 서비스만 테스트하고 싶은데 레포지토리 레이어도 같이 테스트
    @Test
    public void 책등록하기_테스트() {
        // given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("title");
        dto.setAuthor("wuga");

        // stub
        MailSenderSutb mailSenderSutb = new MailSenderSutb();

        // when
        BookService bookService = new BookService(bookRepository, mailSenderSutb);
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        // then
        assertEquals(dto.getTitle(), bookRespDto.getTitle());
        assertEquals(dto.getAuthor(), bookRespDto.getAuthor());
    }

}
