package com.jpql.dto.cart;


import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CartAndUserDto {

    private List<CartItemsDto> cartItems;
    private double totalCost;
    

}
