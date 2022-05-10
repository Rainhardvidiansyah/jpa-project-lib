package com.jpql.controller.cart;


import javax.servlet.http.HttpServletRequest;

import com.jpql.Repository.UserRepo;
import com.jpql.dto.cart.CartDto;
import com.jpql.dto.cart.EditQuantityCartItemsDto;
import com.jpql.dto.errorhandling.ResponseData;
import com.jpql.dto.errorhandling.ResponseMessage;
import com.jpql.dto.cart.CartAndUserDto;
import com.jpql.entities.cart.CartItems;
import com.jpql.service.cart.CartItemsService;
import com.jpql.service.cart.DeleteCartItemsService;
import com.jpql.service.cart.EditQuantityInCartItemsService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private DeleteCartItemsService deleteService;

    @Autowired
    private EditQuantityInCartItemsService editQuantityService;

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
    public ResponseEntity<?> addProductToCart(@RequestBody CartDto cart){
        String email = request.getUserPrincipal().getName();
        cartItemsService.addToCart(cart, email);
        return new ResponseEntity<>(new ResponseMessage(true, "Product added to your cart"), HttpStatus.OK);
    }

    
    @GetMapping("/total/cost")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DEV')")
    public ResponseEntity<?> getCartWithUser(){
        ResponseData<CartAndUserDto> r = new ResponseData<>();
        String email = request.getUserPrincipal().getName();
        User user = userRepo.findByEmail(email).orElse(null);
        r.setStatus(true);
        if(r.isStatus() == true){
            log.info("Successful in processing data");
        }else{
            log.info("Failed to process data");
        }
        CartAndUserDto cartAndUserDto = getUserCartItems.getUserCartItemsAndPrice(user);
        r.setPayload(cartAndUserDto);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @DeleteMapping("delete/{cartitemsid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DEV')")
    public ResponseEntity<?> deleteItems(@PathVariable("cartitemsid") Long cartItemsId){
        String email = request.getUserPrincipal().getName();
        User user = userRepo.findByEmail(email).orElse(null);
        deleteService.deleteItemsInCart(cartItemsId, user);
        return new ResponseEntity<>(new ResponseMessage(true,  user.getFullName() + " just removed product successfully"), HttpStatus.OK);
    }

    @PutMapping("delete/quantity/{cartItemsid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DEV')")
    public ResponseEntity<?> editQuantityInCart(@PathVariable("cartItemsid") Long cartItemsId, 
    @RequestBody EditQuantityCartItemsDto editQuantityCart){
        String email = request.getUserPrincipal().getName();
        User user = userRepo.findByEmail(email).orElse(null);
        CartItems cartItems = new CartItems();
        cartItems.setQuantity(editQuantityCart.getQuantity());
        editQuantityService.editQuantity(cartItemsId, user, cartItems.getQuantity());
        log.info("This user is: {} ", user.getFullName());
        log.info("Removed product: ", cartItems.getCartItemsId() + " " + cartItems.getQuantity());
        return new ResponseEntity<>(new ResponseMessage(true, user.getFullName() + " just removed product successfully"), HttpStatus.OK);
    }
    
}
