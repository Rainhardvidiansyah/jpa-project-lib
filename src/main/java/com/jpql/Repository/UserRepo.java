package com.jpql.Repository;

import java.util.Optional;

import com.jpql.usermodel.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{

    Optional <User> findByEmail(String email);

   @Modifying
   @Query("UPDATE User u SET u.isEnabled = true WHERE u.email = ?1")
   int updateEnabledUserToTrue(String email);

    
}
