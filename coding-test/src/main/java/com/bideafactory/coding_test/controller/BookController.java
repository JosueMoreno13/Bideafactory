package com.bideafactory.coding_test.controller;

import com.bideafactory.coding_test.model.service.IBookService;
import com.bideafactory.coding_test.payload.book.BookRequest;
import com.bideafactory.coding_test.payload.book.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bideafactory/book")
@RequiredArgsConstructor
public class BookController {

    private final IBookService iBookService;

    @PostMapping
    public Mono<ResponseEntity<BookResponse>> createAccount(@RequestBody BookRequest bookRequest) {
        return iBookService.addBook(bookRequest).map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
