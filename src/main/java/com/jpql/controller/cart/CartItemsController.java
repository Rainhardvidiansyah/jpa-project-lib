package com.jpql.controller.cart;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletRequest;

import com.jpql.Repository.cartitem.CartItemsRepo;
import com.jpql.dto.cart.CartDto;
import com.jpql.entities.cart.CartItems;
import com.jpql.entities.product.ProductEntity;
import com.jpql.service.cart.CartItemsService;
import com.jpql.service.product.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartitem")
public class CartItemsController {

    private static final Logger log = LoggerFactory.getLogger(CartItemsController.class);

    @Autowired
    private CartItemsService cartItemsService;

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ServletRequest servletRequest;

    @PostMapping("/addingproduct")
    public ResponseEntity<?> addProductToCart(@RequestBody CartDto cart){
        cartItemsService.addToCart(cart);
        return ResponseEntity.ok().body("Berhasil");
    }
    
}
