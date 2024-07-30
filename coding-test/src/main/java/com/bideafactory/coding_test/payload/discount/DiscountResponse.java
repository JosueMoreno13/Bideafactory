package com.bideafactory.coding_test.payload.discount;

import lombok.Data;

@Data
public class DiscountResponse {

    private String houseId;
    private String discountCode;
    private String id;
    private String userId;
    private boolean status;

}
