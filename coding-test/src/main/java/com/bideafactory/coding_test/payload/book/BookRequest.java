package com.bideafactory.coding_test.payload.book;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @Id
    @NotBlank
    @Size(min = 9, max = 10, message = "The maximum field length is 10")
    private String id;
    @NotBlank
    @Size(min = 2, max = 50, message = "The maximum field length is 50")
    private String name;
    @NotBlank
    @Size(min = 2, max = 50, message = "The maximum field length is 50")
    private String lastname;
    @Min(18)
    @Max(100)
    private int age;
    @NotBlank
    @Size(min = 9, max = 20, message = "The maximum field length is 20")
    private String phoneNumber;
    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe estar en el formato yyyy-MM-dd")
    private String starDate;
    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe estar en el formato yyyy-MM-dd")
    private String endDate;
    @NotBlank
    @Size(min = 6, max = 15, message = "The maximum field length is 15")
    private String houseId;
    @NotBlank
    @Size(min = 8, max = 8, message = "The maximum field length is 8")
    private String discountCode;
}
