package com.jpql.service.cart;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.jpql.Repository.UserRepo;
import com.jpql.Repository.cartitem.CartItemsRepo;
import com.jpql.dto.cart.CartItemsDto;
import com.jpql.dto.cart.CartItemsProductSpecs;
import com.jpql.dto.cart.CartAndUserDto;
import com.jpql.entities.cart.CartItems;
import com.jpql.usermodel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetUserCartItems {

    @Autowired
    private CartItemsRepo cartItemsRepo;

    @Autowired
    private UserRepo userRepo;

    public CartItems getCartItemsUser(CartItems cartItems, String email){
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null){
            throw new RuntimeException("USER IS NOT FOUND!");
        }
        List<CartItems> cartList = new ArrayList<>();
        for(CartItems cartItems2 : cartList){   
        }
        return null;
    }

    public CartAndUserDto getUserCartItemsAndPrice(User user){
        List<CartItems> cartList = cartItemsRepo.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItemsProductSpecs> cartAndProductSpecs = new ArrayList<>();
        double totalCost = 0;

        for(CartItems cartItems : cartList){
            CartItemsProductSpecs cartItemsProductSpecs = new CartItemsProductSpecs(cartItems);
            totalCost += cartItemsProductSpecs.getQuantity() * cartItems.getProductEntity().getPrice();
            cartAndProductSpecs.add(cartItemsProductSpecs);
        }
        CartAndUserDto cartAndUserDto = new CartAndUserDto();
        cartAndUserDto.setTotalCost(totalCost);
        cartAndUserDto.setCartItems(cartAndProductSpecs);
        return cartAndUserDto;
    }
    
}
