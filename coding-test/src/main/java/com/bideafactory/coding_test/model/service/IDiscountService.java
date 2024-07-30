package com.bideafactory.coding_test.model.service;

import com.bideafactory.coding_test.payload.discount.DiscountRequest;
import com.bideafactory.coding_test.payload.discount.DiscountResponse;
import reactor.core.publisher.Mono;

public interface IDiscountService {

    Mono<DiscountResponse> validateDiscount(DiscountRequest discountRequest);
}
