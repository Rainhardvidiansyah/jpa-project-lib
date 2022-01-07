package com.jpql.entities.product;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jpql.entities.cart.CartItems;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "product_tbl")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String productDescription;

    @Enumerated(EnumType.STRING)
    private Level level;

    private Double price;

    private int stocks;

    private LocalDate madeAt;

    private LocalDate expiredAt;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<CartItems> cartItems;

    public Level showLevel(){
        for(Level level : Level.values()){
            level.toString();
        }
        return level;
    }



    public ProductEntity(String productName, String productDescription, 
                        Double price, int stocks, LocalDate madeAt, 
                        LocalDate expiredAt) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.stocks = stocks;
        this.madeAt = madeAt;
        this.expiredAt = expiredAt;
    }

    
    
    
}
