package com.jpql.Repository;

import java.util.Optional;

import com.jpql.usermodel.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

    Optional <User> findByEmail(String email);
    
}
