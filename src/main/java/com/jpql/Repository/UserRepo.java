package com.jpql.Repository;

import com.jpql.usermodel.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo {

    public User findByEmail(String email);
    
}
