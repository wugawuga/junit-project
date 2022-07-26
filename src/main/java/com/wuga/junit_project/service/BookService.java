package com.wuga.junit_project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wuga.junit_project.domain.Book;
import com.wuga.junit_project.domain.BookRepository;
import com.wuga.junit_project.util.MailSender;
import com.wuga.junit_project.web.dto.BookRespDto;
import com.wuga.junit_project.web.dto.BookSaveReqDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final MailSender mailSender;

    // 1. 책등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookRespDto 책등록하기(BookSaveReqDto bookSaveReqDto) {
        Book bookEntity = bookRepository.save(bookSaveReqDto.toEntity());
        if (bookEntity != null) {
            if (!mailSender.send()) {
                throw new RuntimeException("메일이 전송되지 않았습니다");
            }
        }
        return new BookRespDto().toDto(bookEntity);
    }

    // 2. 책목록보기
    @Transactional
    public List<BookRespDto> 책목록보기() {
        return bookRepository.findAll().stream()
                .map((book) -> new BookRespDto().toDto(book))
                .collect(Collectors.toList());
    }

    // 3. 책하나
    public BookRespDto 책하나보기(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return new BookRespDto().toDto(book.get());
        } else {
            throw new RuntimeException("해당 책을 찾을 수 없습니다");
        }
    }

    // 4. 책삭제
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책삭제(Long id) {
        bookRepository.deleteById(id);
    }

    // 5. 책수정
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책수정(Long id, BookSaveReqDto dto) {
        Optional<Book> bookEntity = bookRepository.findById(id);
        if (bookEntity.isPresent()) {
            Book book = bookEntity.get();
            book.update(dto.getTitle(), dto.getAuthor());
        } else {
            throw new RuntimeException("해당 책을 찾을 수 없습니다");
        }
    }
}
