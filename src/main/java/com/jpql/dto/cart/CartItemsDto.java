package com.jpql.dto.cart;

import com.jpql.entities.cart.CartItems;
import com.jpql.entities.product.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor
public class CartItemsDto {
    
    private long productId;
    private int quantity;
    private String productName;
    private double price;

    public CartItemsDto(CartItems cartItems){
        this.setProductId(cartItems.getProductEntity().getProductId());
        this.quantity = cartItems.getQuantity();
        this.setProductName(cartItems.getProductEntity().getProductName());
        this.setPrice(cartItems.getProductEntity().getPrice());
    }

}
