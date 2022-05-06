package com.jpql.controller.cart;


import javax.servlet.http.HttpServletRequest;

import com.jpql.Repository.UserRepo;
import com.jpql.dto.cart.CartDto;
import com.jpql.dto.cart.CartAndUserDto;
import com.jpql.entities.cart.CartItems;
import com.jpql.service.cart.CartItemsService;
import com.jpql.service.cart.GetUserCartItems;
import com.jpql.service.product.ProductService;
import com.jpql.usermodel.User;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartItemsController {

    private static final Logger log = LoggerFactory.getLogger(CartItemsController.class);

    @Autowired
    private CartItemsService cartItemsService;

    @Autowired
    private GetUserCartItems getUserCartItems;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addproduct")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DEV')")
    public ResponseEntity<?> addProductToCart(@RequestBody CartDto cart, User user){
        //User user = new User();
        String email = request.getUserPrincipal().getName();
        
        cartItemsService.addToCart(cart, email);
        return ResponseEntity.ok().body(cart + " berhasil dibuat");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> showUserAndPrice(String email){
        CartItems cartItems = new CartItems();
        cartItemsService.showUserAndProductAdded(cartItems.getQuantity(), email);
        return ResponseEntity.ok().body("body");
    }

    @GetMapping("/total/cost")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DEV')")
    public ResponseEntity<CartAndUserDto> getCartWithUser(){
        String email = request.getUserPrincipal().getName();
        User user = userRepo.findByEmail(email).orElse(null);
        
        CartAndUserDto c = getUserCartItems.getUserCartItemsAndPrice(user);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    
}
