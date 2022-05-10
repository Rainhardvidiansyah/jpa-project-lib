package com.jpql.dto.cart;

import java.time.LocalDate;

import com.jpql.entities.product.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class ProductForCartDto {

    private String productName;

    private String productDescription;

    private double price;

    private LocalDate madeAt;

    private LocalDate expiredAt;

    public ProductForCartDto(ProductEntity product){
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.price = product.getPrice();
        this.madeAt = product.getMadeAt();
        this.expiredAt = product.getExpiredAt();
    }
    
}
