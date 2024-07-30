package com.bideafactory.coding_test.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    private static final long serialVersionUID = -3255948451901901271L;

    @Id
    @Column(length = 10)
    private String id;
    @Column(length = 50)
    private String name;
    @Column(length = 50)
    private String lastname;
    private int age;
    @Column(length = 20)
    private String phoneNumber;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe estar en el formato yyyy-MM-dd")
    private String starDate;
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe estar en el formato yyyy-MM-dd")
    private String endDate;
    @Column(length = 15)
    private String houseId;
    @Column(length = 8)
    private String discoundCode;
}
