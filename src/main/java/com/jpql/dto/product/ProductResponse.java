package com.jpql.dto.product;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {

    private String productName;

    private String productDescription;

    private Double price;

    private int stocks;

    private LocalDate madeAt;

    private LocalDate expiredAt;
    
}
