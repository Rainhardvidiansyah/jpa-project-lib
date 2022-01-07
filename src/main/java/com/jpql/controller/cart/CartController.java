package com.jpql.controller.cart;

import java.util.ArrayList;
import java.util.List;

import com.jpql.Repository.cart.CartRepo;
import com.jpql.dto.cart.CartDto;
import com.jpql.entities.cart.CartEntity;
import com.jpql.entities.product.ProductEntity;
import com.jpql.service.cart.CartService;
import com.jpql.service.product.ProductService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shoppingcart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ModelMapper modelMapper;

    

    
}


// @PostMapping("/add/{productId}")
    // public void addCart(@RequestBody CartDto cartDto, @PathVariable("productId") Long productId){
    //     CartEntity cartEntity = modelMapper.map(cartDto, CartEntity.class);
    //     productService.setCartIdToProduct(productId, cartEntity);
    // }

    // @PostMapping("/addcart/{productId}")
    // public CartEntity addToCart(@RequestBody CartEntity cartEntity, @PathVariable("productId") Long productId){
    //     ProductEntity productEntity = productService.findProductById(productId);
    //     if(productEntity == null){
    //         throw new RuntimeException("Product Tidak ada");
    //     }
    //     return cartService.addToCart(cartEntity, productId);
    // }
