package com.jpql.service;

import com.jpql.Repository.UserRepo;
import com.jpql.Repository.VerificationTokenRepo;
import com.jpql.service.email.EmailService;
import com.jpql.service.verificationtoken.VerificationTokenService;
import com.jpql.usermodel.Role;
import com.jpql.usermodel.User;
import com.jpql.usermodel.VerificationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VerificationTokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email).orElseThrow( () ->
            new UsernameNotFoundException(
                String.format("User dengan '$s' Tidak Ditemukan", email))
        );
    }
    

    /**
    TODO: ADD EMAIL SERVICE AND VERIFICATION SERVICE
     */
    public User registration(User user){
        boolean userExist = userRepo.findByEmail(user.getEmail()).isPresent();
        if(userExist){
            throw new IllegalStateException("User dengan Telah Terdaftar...");
        }
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        VerificationToken verificationToken = new VerificationToken(user);
        tokenService.saveToken(verificationToken);

        emailService.sendEmail(user.getEmail(), "Verification Email", "Mohon Klik  "+
            "link token ini: " + 
            "http://localhost:8080/confirm-account?=token:" + verificationToken.getToken());
        user.setRole(Role.USER);
        return userRepo.save(user);
    }

    


    public int updateEnableUser(String email){
        return userRepo.updateEnabledUserToTrue(email);
    }

    
   
}
