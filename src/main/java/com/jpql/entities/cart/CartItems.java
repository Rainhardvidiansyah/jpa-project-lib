package com.jpql.entities.cart;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jpql.entities.product.ProductEntity;
//import com.jpql.helper.audit.Auditing;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_items")
@Getter @Setter
public class CartItems{
//extends Auditing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartItems_id")
    private Long cartItemsId;

    private int Quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private ShoppingCart cartEntity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private ProductEntity productEntity;

    
}


/**
 One cart item references one product. But a single product is referenced by several cart items. 
 So it's a one-to-many association.

One shopping cart as several items, 
and an item is part of one shopping cart, 
so it's also a one-to-many association.

When you have a bidirectional OneToMany association, 
the owner side of the association is always the many side. 
The owner side of an association is the side where there is no mappedBy attribute. 
Indeed, mappedBy means "I'm just the other side of an association, which is already mapped by the following attribute". 
Note that the way the asociation is mapped (join column, join table) must only be defined on the owner side, 
where the mappedBy attribute is absent.

When you have a unidirectional association, 
there is only one place where the mapping can be defined, 
and the mappedBy attribute is thus never used.

Source: 
https://stackoverflow.com/questions/7308804/setting-the-correct-jpa-mapping-for-shopping-cart-items-and-product
 */