package com.jpql.Repository.cartitem;

import com.jpql.entities.cart.CartItems;
import com.jpql.usermodel.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemsRepo extends JpaRepository<CartItems, Long>{


    @Query("select c from CartItems c where c.user =  ?1 and c.quantity = ?2")
    public CartItems showUser(User user, int quantity);

    //@Query("select c from CartItems c  where c.price = ?1")
    public CartItems findAllByQuantity(int quantity);


    
}
