package com.bideafactory.coding_test.payload.book;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {

    private String code;
    private String message;
    private String error;

    public BookResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BookResponse(String code, String message, String error) {
        this.code = code;
        this.message = message;
        this.error = error;
    }
}
