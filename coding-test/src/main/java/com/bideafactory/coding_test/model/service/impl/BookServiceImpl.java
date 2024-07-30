package com.bideafactory.coding_test.model.service.impl;

import com.bideafactory.coding_test.model.entity.Book;
import com.bideafactory.coding_test.model.repository.IBookRepository;
import com.bideafactory.coding_test.model.service.IBookService;
import com.bideafactory.coding_test.payload.book.BookRequest;
import com.bideafactory.coding_test.payload.book.BookResponse;
import com.bideafactory.coding_test.payload.discount.DiscountRequest;
import com.bideafactory.coding_test.payload.discount.DiscountResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final IBookRepository iBookRepository;

    private final DiscountServiceImpl discountService;

    @Override
    public Mono<BookResponse> addBook(BookRequest bookRequest) {
        logger.info("Iniciando proceso de reserva para el usuario: {}, casa: {}", bookRequest.getId(), bookRequest.getHouseId());
        if (isNotNullorEmpty(bookRequest.getDiscountCode())) {
            return discountService.validateDiscount(mapToDiscountRequest(bookRequest)).flatMap(response -> {
                if (response.isStatus()) {
                    logger.info("Descuento validado correctamente. Procediendo con la reserva.");
                    iBookRepository.save(mapBookEntity(bookRequest));
                    return Mono.just(mapSuccessBookResponse());
                } else {
                    logger.warn("Descuento inválido para la reserva. Código: {}", bookRequest.getDiscountCode());
                    return Mono.just(mapFailureBookResponse());
                }
            });
        } else {
            iBookRepository.save(mapBookEntity(bookRequest));
            return Mono.just(mapSuccessBookResponse());
        }

    }

    private DiscountRequest mapToDiscountRequest(BookRequest bookingRequest) {
        return new DiscountRequest(bookingRequest.getId(), bookingRequest.getHouseId(), bookingRequest.getDiscountCode());
    }

    private Book mapBookEntity(BookRequest bookingRequest) {
        return new Book(bookingRequest.getId(), bookingRequest.getName(), bookingRequest.getLastname(), bookingRequest.getAge(), bookingRequest.getPhoneNumber(),
                bookingRequest.getStarDate(), bookingRequest.getEndDate(), bookingRequest.getHouseId(), bookingRequest.getDiscountCode());
    }

    private BookResponse mapSuccessBookResponse() {
        return new BookResponse("200", "Book Accepted");
    }

    private BookResponse mapFailureBookResponse() {
        return new BookResponse("400", "Invalid Discount", "Conflict");
    }

    private boolean isNotNullorEmpty(String string) {
        return string != null && !string.isEmpty();
    }
}
