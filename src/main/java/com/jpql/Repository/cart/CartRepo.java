package com.jpql.Repository.cart;

import com.jpql.entities.cart.CartEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<CartEntity, Long>{

    /*
    FUNCTIONALITY TO ADD PRODUCT IN CART IS HERE
    USING A QUERY AND NEW METHOD
     */
    
    
    
}
