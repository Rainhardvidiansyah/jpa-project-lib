package com.jpql.entities.product;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jpql.entities.cart.CartItems;
import com.jpql.helper.audit.Auditing;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.MAX;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "product_tbl")
public class ProductEntity extends Auditing implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    @Lob
    @Column(name = "product_image", length = Integer.MAX_VALUE)
    private String picture;

    private String productDescription;

    @Enumerated(EnumType.STRING)
    private Level level;

    private double price;

    private int stocks;

    private LocalDate madeAt;

    private LocalDate expiredAt;

//    @ManyToOne
//    @JoinColumn(name = "cart_items_id") //TAMBAHIN REFERENCECOLUMNNAME
//    private CartItems cartItems;

    public Level showLevel(){
        for(Level level : Level.values()){
            level.toString();
        }
        return level;
    }

    public ProductEntity(String productName, String picture,String productDescription, 
                        Double price, int stocks, LocalDate madeAt, 
                        LocalDate expiredAt) {
        this.productName = productName;
        this.picture = picture;
        this.productDescription = productDescription;
        this.price = price;
        this.stocks = stocks;
        this.madeAt = madeAt;
        this.expiredAt = expiredAt;
    }

    
    
    
}
