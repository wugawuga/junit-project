package com.wuga.junit_project.domain;

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
        System.out.println("책등록테스트 실행");
    }
}
