package com.jpql.service;



import com.jpql.Repository.UserRepo;
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
    private PasswordEncoder passwordEncoder;
    
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VerificationTokenService tokenService;

    @Autowired
    private EmailService emailService;

    

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
        // String encodePassword = passwordEncoder.encode(user.getPassword());
        // user.setPassword(encodePassword);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        VerificationToken verificationToken = new VerificationToken(user);
        tokenService.saveToken(verificationToken);

        emailService.sendEmail(user.getEmail(), "Verification Email", "Mohon Klik  "+
            "link token ini: " + 
            "http://localhost:8080/confirm-account?=token:" + verificationToken.getToken());
        user.setRole(Role.USER);
        return userRepo.save(user);
    }



    /*
     TODO: CREATE CONFIRMATION TOKEN, CONFIRMED_AT
     AND CHANGE ENABLE USER TO TRUE
     if(User.enabled == false){
         return badcredential;
     }else{
         return redirect to welcome_page;
     }
     */
    public void confirmationToken(String token, String email){
        VerificationToken verificationToken = tokenService.findToken(token);
        User user = new User();
        if(verificationToken != null){
            updateEnableUser(verificationToken.getUser().getEmail());
            tokenService.updateTokenConfirmedAt(verificationToken.getUser().getEmail());
        }else{
            throw new RuntimeException("Token Not Found...");
        }
    }


    


    public int updateEnableUser(String email){
        return userRepo.updateEnabledUserToTrue(email);
    }

    
   
}
