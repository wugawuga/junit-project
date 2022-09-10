package com.wuga.junit_project.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wuga.junit_project.domain.Book;
import com.wuga.junit_project.domain.BookRepository;
import com.wuga.junit_project.util.MailSender;
import com.wuga.junit_project.web.dto.BookRespDto;
import com.wuga.junit_project.web.dto.BookSaveReqDto;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MailSender mailSender;

    // 문제점: 서비스만 테스트하고 싶은데 레포지토리 레이어도 같이 테스트
    @Test
    public void 책등록하기_테스트() {
        // given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("title");
        dto.setAuthor("wuga");

        // stub(가설)
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        // when
        BookRespDto bookRespDto = bookService.책등록하기(dto);

        // then
        // assertEquals("test", bookRespDto.getTitle());
        // assertEquals(dto.getAuthor(), bookRespDto.getAuthor());

        assertThat(bookRespDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookRespDto.getAuthor()).isEqualTo(dto.getAuthor());
    }

    @Test
    public void 책목록보기_테스트() {
        // given

        // stub
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "junit강의", "우가우가"));
        books.add(new Book(2L, "wuga강의", "wugawuga"));

        when(bookRepository.findAll()).thenReturn(books);

        // when
        List<BookRespDto> bookRespDtos = bookService.책목록보기();

        // then
        assertThat(bookRespDtos.get(0).getTitle()).isEqualTo("junit강의");
        assertThat(bookRespDtos.get(0).getAuthor()).isEqualTo("우가우가");
        assertThat(bookRespDtos.get(1).getTitle()).isEqualTo("wuga강의");
        assertThat(bookRespDtos.get(1).getAuthor()).isEqualTo("wugawuga");
    }
}
