package com.jpql.service.verificationtoken;

import java.time.LocalDateTime;

import com.jpql.Repository.VerificationTokenRepo;
import com.jpql.usermodel.VerificationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService {

    @Autowired
    private VerificationTokenRepo tokenRepo;
    


    /**
     NOTE:
     PARAMETER WITH VERIFICATION TOKEN MUST BE WRITTEN.
     THIS IS BECAUESE SYSTTEM SAVES ALL OBJECT OF VERIFICATION TOKEN
     */
    public VerificationToken saveToken(VerificationToken verificationToken){
        return tokenRepo.save(verificationToken);
    }

    public VerificationToken findToken(String token){
        return tokenRepo.findByToken(token);
    }

    public int updateTokenConfirmedAt(String token){
        return tokenRepo.updateTokenConfirmedAtAndToken(token, LocalDateTime.now());
    }


    
}
