package com.jpql.entities.product;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "product_tbl")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String productDescription;

    private Double price;

    private int stocks;

    private LocalDate madeAt;

    private LocalDate expiredAt;

    public ProductEntity(String productName, String productDescription, Double price, int stocks, LocalDate madeAt,
            LocalDate expiredAt) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.stocks = stocks;
        this.madeAt = madeAt;
        this.expiredAt = expiredAt;
    }

    
    
    
}
