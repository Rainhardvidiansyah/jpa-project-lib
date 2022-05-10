package com.jpql.dto.cart;

import com.jpql.entities.cart.CartItems;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class CartItemsProductSpecs {

    private Long cartItemsId;
    private int quantity;
    private String productName;
    private String productDesc;
    private double unitPrice;

    public CartItemsProductSpecs(CartItems cartItems){
        this.cartItemsId = cartItems.getCartItemsId();
        this.quantity = cartItems.getQuantity();
        this.productName = cartItems.getProductEntity().getProductName();
        this.productDesc = cartItems.getProductEntity().getProductDescription();
        this.unitPrice = cartItems.getProductEntity().getPrice();
    }


    


}
