package com.jpql.service.cart;

import java.util.Optional;

import javax.transaction.Transactional;

import com.jpql.Repository.cartitem.CartItemsRepo;
import com.jpql.entities.cart.CartItems;
import com.jpql.usermodel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditQuantityInCartItemsService {


    @Autowired
    private CartItemsRepo cartRepo;

    @Transactional
    public void editQuantity(Long cartItemsId, User user, int quantity){
        Optional<CartItems> listCartItems = cartRepo.findById(cartItemsId);
        if(listCartItems.isEmpty()){
            throw new RuntimeException("Product not found");
        }
        CartItems cartItems = listCartItems.get();
        if(cartItems.getUser() != user){
            throw new RuntimeException("This product doens't belong to you!");
        }
        int result = cartItems.getQuantity() - quantity;
        cartItems.setQuantity(result);
        cartRepo.save(cartItems);
    }


}
