package com.jpql.service.cart;


import com.jpql.Repository.cartitem.CartItemsRepo;
import com.jpql.entities.cart.CartItems;
import com.jpql.entities.product.ProductEntity;
import com.jpql.service.product.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemsService {


    @Autowired
    private CartItemsRepo cartItemsRepo;

    @Autowired
    private ProductService productService;

    public CartItems addProduct(CartItems cartItems, Long productId){
        ProductEntity findProductId = productService.findProductById(productId);
        //ProductEntity productEntity = new ProductEntity();
        cartItems.setProductEntity(findProductId);
        return cartItemsRepo.save(cartItems);
    }

    
    
}
