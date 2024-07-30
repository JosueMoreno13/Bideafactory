package com.bideafactory.coding_test.payload.discount;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountRequest {
    private String userId;
    private String houseId;
    private String discountCode;
}
