package com.jpql.service.cart;


import com.jpql.Repository.cart.ShoppingCartRepo;
import com.jpql.service.product.ProductService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    /**
     THIS FILE SHOULD HANDLE:
     1. PRODUCT CHOSEN BY USER
     2. PRODUCT ADDED TO CART BY USER
     */

    @Autowired
    private ShoppingCartRepo cartRepo;

    @Autowired
    private ProductService productService;


}

