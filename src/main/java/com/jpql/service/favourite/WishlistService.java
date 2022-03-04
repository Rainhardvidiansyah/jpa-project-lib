package com.jpql.service.favourite;


import com.jpql.Repository.favourite.WishlistRepo;


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
