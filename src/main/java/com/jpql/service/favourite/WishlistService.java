package com.jpql.service.favourite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.jpql.Repository.favourite.WishlistRepo;
import com.jpql.entities.favourite.UserWishlist;
import com.jpql.entities.product.ProductEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WishlistService {

    @Autowired
    private WishlistRepo wishlistRepo;

    
    // public UserWishlist wishlist(UserWishlist uw){
    //     ProductEntity product = new ProductEntity();
    //     Long product_id = product.getProductId();
    //     List<Long> addAgain = new ArrayList<>();
    //     addAgain.add(product_id);
    //     if(product_id == null){
    //         throw new RuntimeException("Product tidak ditemukan");
    //     }
    //     return wishlistRepo.save(uw);
    // }

    
}
