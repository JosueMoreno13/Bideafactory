package com.bideafactory.coding_test;

import com.bideafactory.coding_test.model.entity.Book;
import com.bideafactory.coding_test.model.repository.IBookRepository;
import com.bideafactory.coding_test.model.service.impl.BookServiceImpl;
import com.bideafactory.coding_test.model.service.impl.DiscountServiceImpl;
import com.bideafactory.coding_test.payload.book.BookRequest;
import com.bideafactory.coding_test.payload.book.BookResponse;
import com.bideafactory.coding_test.payload.discount.DiscountRequest;
import com.bideafactory.coding_test.payload.discount.DiscountResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@SpringBootTest
class MicroservicesApplicationTests {
    @InjectMocks
    private BookServiceImpl bookingService;

    @Mock
    private DiscountServiceImpl discountService;

    @Mock
    private IBookRepository bookingRepository;

    @Test
    void contextLoads() {
        Book request = new Book("14564088-04", "Gonzalo", "Perez", 33, "56988123222",
                "2022-03-04", "2022-03-04", "21332", "D0542A23");
        BookResponse expectedResponse = new BookResponse("200", "Book Accepted");
        DiscountResponse discountResponse = new DiscountResponse();
        discountResponse.setStatus(true);
        when(bookingRepository.save(any(Book.class))).thenReturn(request);
        when(discountService.validateDiscount(any(DiscountRequest.class))).thenReturn(Mono.just(discountResponse));
        Mono<BookResponse> response = bookingService.addBook(new BookRequest("14564088-04", "Gonzalo", "Perez", 33, "56988123222",
                "2022-03-04", "2022-03-04", "21332", "D0542A23"));

        StepVerifier.create(response)
                .expectNext(expectedResponse)
                .verifyComplete();

        verify(discountService).validateDiscount(any(DiscountRequest.class));
        verify(bookingRepository).save(any(Book.class));
    }

}
