package com.jpql.Repository;


import java.time.LocalDateTime;

import javax.transaction.Transactional;

import com.jpql.usermodel.VerificationToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepo 
    extends JpaRepository<VerificationToken, Long>{

        
        VerificationToken findByToken(String token);

        @Transactional
        @Modifying
        @Query("UPDATE VerificationToken v SET v.confirmedAt = ?2 WHERE v.token = ?1")
        int updateTokenConfirmedAtAndToken(String token, LocalDateTime confirmedAt);

    
}
