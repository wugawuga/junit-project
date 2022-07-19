package com.wuga.junit_project.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // db 관련 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {
    
    @Autowired
    private BookRepository bookRepository;

    // 1. 책 등록
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
}
