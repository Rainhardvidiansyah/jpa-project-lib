package com.jpql.service.cart;

import java.util.Optional;

import com.jpql.Repository.cartitem.CartItemsRepo;
import com.jpql.entities.cart.CartItems;
import com.jpql.usermodel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCartItemsService {

    @Autowired
    private CartItemsRepo cartRepo;

    public void deleteItemsInCart(Long cartItemsId, User user){
        Optional<CartItems> cart = cartRepo.findById(cartItemsId);
        if(!cart.isPresent()){
            throw new RuntimeException("No items in cart");
        }
        CartItems cartItems = cart.get();
        if(cartItems.getUser() != user){
            throw new RuntimeException("This items doesn't belong to this user");
        }
        cartRepo.delete(cartItems);
    }
    
}
