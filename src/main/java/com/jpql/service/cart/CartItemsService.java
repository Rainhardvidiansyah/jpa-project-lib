package com.jpql.service.cart;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.jpql.Repository.cartitem.CartItemsRepo;
import com.jpql.dto.cart.CartDto;
import com.jpql.entities.cart.CartItems;
import com.jpql.entities.product.ProductEntity;
import com.jpql.service.product.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service

public class CartItemsService {

    private static Logger log = LoggerFactory.getLogger(CartItemsService.class);

    @Autowired
    private CartItemsRepo cartItemsRepo;

    @Autowired
    private ProductService productService;

    public void addToCart(CartDto cartDto){
        ProductEntity product = productService.findProductById(cartDto.getProductId());
        if(product == null){
            throw new RuntimeException("Product tidak ada/habis");
        }else if(cartDto.getQuantity() > product.getStocks()){
            throw new RuntimeException("Jumlah pesanan melebihi stok barang!");
        }
        int addProduct = cartDto.getQuantity();
        int productStocks = product.getStocks();
        int substractResult = productStocks - addProduct;
        CartItems cart = new CartItems();
        cart.setQuantity(cartDto.getQuantity());
        cart.setProductEntity(product);
        product.setStocks(substractResult);
        cartItemsRepo.save(cart);
        productService.saveProduct(product);
    }
    
    
     
       
   

    
    
}
