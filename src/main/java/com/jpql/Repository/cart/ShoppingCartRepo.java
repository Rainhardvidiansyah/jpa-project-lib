package com.jpql.Repository.cart;

import com.jpql.entities.cart.ShoppingCart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long>{

    /*
    FUNCTIONALITY TO ADD PRODUCT IN CART IS HERE
    USING A QUERY AND NEW METHOD
     */
    
    
    
}
