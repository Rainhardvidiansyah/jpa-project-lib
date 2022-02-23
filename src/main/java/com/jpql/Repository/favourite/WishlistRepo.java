package com.jpql.Repository.favourite;

import com.jpql.entities.favourite.UserWishlist;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepo extends JpaRepository<UserWishlist, Long>{
    
    
}
