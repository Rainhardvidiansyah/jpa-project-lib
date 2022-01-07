package com.jpql.Repository.cartitem;

import com.jpql.entities.cart.CartItems;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepo extends JpaRepository<CartItems, Long>{
    
}
