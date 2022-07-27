package com.wuga.junit_project.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest // db 관련 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void 책_데이터_준비() {
        String title = "junit5";
        String author = "wuga";

        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    }

    @Test
    public void 책등록_테스트() {
        // 데이터 준비
        String title = "junit5";
        String author = "wuga";

        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();

        // 테스트 실행
        Book bookEntity = bookRepository.save(book);

        // 검증
        assertEquals(title, bookEntity.getTitle());
        assertEquals(author, bookEntity.getAuthor());
    }

    @Test
    public void 책목록_테스트() {
        // 데이터 준비
        String title = "junit5";
        String author = "wuga";

        // 테스트 실행
        List<Book> bookEntity = bookRepository.findAll();

        // 검증
        assertEquals(title, bookEntity.get(0).getTitle());
        assertEquals(author, bookEntity.get(0).getAuthor());
    }

    @Sql("classpath:db/tableInit.sql")
    @Test
    public void 책한권보기_테스트() throws Exception {
        // 데이터 준비
        String title = "junit5";
        String author = "wuga";

        // 테스트 실행
        Book bookEntity = bookRepository.findById(1L).get();

        // 검증
        assertEquals(title, bookEntity.getTitle());
        assertEquals(author, bookEntity.getAuthor());
    }
}
