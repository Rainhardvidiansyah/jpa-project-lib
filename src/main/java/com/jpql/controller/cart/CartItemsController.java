package com.jpql.controller.cart;

import com.jpql.entities.cart.CartItems;
import com.jpql.entities.product.ProductEntity;
import com.jpql.service.cart.CartItemsService;
import com.jpql.service.product.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cartitem")
public class CartItemsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartItemsController.class);

    @Autowired
    private CartItemsService itemsService;

    @Autowired
    private ProductService productService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<?> add(@RequestBody CartItems cartItems, @PathVariable("productId") Long productId){
        ProductEntity product = productService.findProductById(productId);
        if(product == null){
            LOGGER.error("Product Does Not Exist", product);
            return new ResponseEntity<>(product, HttpStatus.BAD_REQUEST);
            
        }
        LOGGER.info(product + "Added", product);
        return new ResponseEntity<>(itemsService.addProduct(cartItems, productId), HttpStatus.OK);
    
    }
    
}
