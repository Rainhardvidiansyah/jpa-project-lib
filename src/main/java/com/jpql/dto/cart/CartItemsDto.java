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
    private Long id;
    private int quantity;
    private ProductEntity productEntity;

    public CartItemsDto(CartItems cartItems){
        this.id = cartItems.getCartItemsId();
        this.quantity = cartItems.getQuantity();
        this.setProductEntity(cartItems.getProductEntity());
    }

}
