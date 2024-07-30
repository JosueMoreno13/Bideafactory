package com.bideafactory.coding_test.model.service;

import com.bideafactory.coding_test.payload.book.BookRequest;
import com.bideafactory.coding_test.payload.book.BookResponse;
import reactor.core.publisher.Mono;

public interface IBookService {

    Mono<BookResponse> addBook(BookRequest bookRequest);
}
