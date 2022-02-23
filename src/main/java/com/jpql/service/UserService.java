package com.jpql.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.transaction.Transactional;

import com.jpql.Repository.UserRepo;
import com.jpql.Repository.role.RoleRepo;
import com.jpql.dto.registration.RegistrationRequest;
import com.jpql.service.email.EmailService;
import com.jpql.service.verificationtoken.VerificationTokenService;
import com.jpql.usermodel.ERole;
import com.jpql.usermodel.Role;
import com.jpql.usermodel.User;
import com.jpql.usermodel.UserDetailsImpl;
import com.jpql.usermodel.VerificationToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
     private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VerificationTokenService tokenService;

    @Autowired
    private EmailService emailService;
    
    @Autowired
    private RoleRepo roleRepo;

    
    

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(() -> 
        new UsernameNotFoundException("Email tidak ditemukan ... "));
        return UserDetailsImpl.build(user);
    }
    
     String subject = "VERIFIKASI EMAIL";
     String  htmlCode = "<p> Klik Link Berikut Untuk mengaktifkan akun Anda: </p>";
     String link = "http://localhost:8080/confirm-account?=token";
     String hyperLinkToSend = htmlCode + " " + link;



    /**
    TODO: ADD EMAIL SERVICE AND VERIFICATION SERVICE
    TODO: MAKE THIS HYPERLINK A REAL ONE -> 
    It is also possible to use setText when sending HTML-mail:
    String html = "Test\n" + text + "\n<a href='http://test.com'>Test.com</a>";
    messageBodyPart.setText(html, "UTF-8", "html");
    */
    public User registration(User user){
        
        boolean userExist = userRepo.findByEmail(user.getEmail()).isPresent();
        if(userExist){
            throw new IllegalStateException("Email dengan Telah Terdaftar...");
        }
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        VerificationToken verificationToken = new VerificationToken(user);
        tokenService.saveToken(verificationToken);
        Date date = new Date();
        emailService.sendEmail(user.getEmail(), this.subject,
        this.hyperLinkToSend + verificationToken.getToken(), date);

        RegistrationRequest request = new RegistrationRequest();
        List<Role> userRole = user.getRole();
        List<Role> roles = new ArrayList<>();
        if(userRole == null){
            Role role = roleRepo.findByName(ERole.USER).orElseThrow(() -> new RuntimeException("message"));
            roles.add(role);
        }
    
        user.setRole(roles);
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
    public VerificationToken confirmationToken(String token){
        VerificationToken verificationToken = tokenService.findToken(token);
        User user = new User();
        if(verificationToken != null){
            tokenService.updateTokenConfirmedAt(token);
            user.setEnabled(true);
        }
        return verificationToken;
    }


    

    /**
     METHOD FOR SET USER.ISENABLED = TRUE
     */
    public int updateEnableUser(String email){
        return userRepo.updateEnabledUserToTrue(email);
    }

    
   
}


 //     VerificationToken vToken = tokenService.findToken(token);
    //     //String newToken = vToken.getToken();
    //     VerificationToken verificationToken = tokenService.findToken(vToken.token);
        
    //     /*
    //     SYARAT CONDITIONAL-NYA HARUSNYA: JIKA USER MENGKLIK TOKEN TERSEBUT
    //     */

    //     if(vToken.getToken() == vToken.getUser().getEmail()){
    //         updateEnableUser(user.getEmail());
    //     }else{
    //         System.out.println("ada yang salah");
    //     }

    
        
        // List<String> strRole = request.getRoles();
        // List<Role> roles = new ArrayList<>();
    
        // if(strRole == null){
        //     Role userRole = roleRepo.findByName(ERole.USER)
        //     .orElseThrow(() -> new RuntimeException("message"));
        //     logger.info("ADDED {}", userRole);

        //     roles.add(userRole);
        // }else{
        //     strRole.forEach(role -> {
        //         switch (role){
        //             case "ADMIN":
        //             Role adminRole = roleRepo.findByName(ERole.ADMIN)
        //             .orElseThrow(() -> new RuntimeException("message"));
        //             roles.add(adminRole);
        //             break;
                    
        //             case "DEV":
        //             Role devRole = roleRepo.findByName(ERole.DEV)
        //             .orElseThrow(() -> new RuntimeException("message"));
        //             roles.add(devRole);
        //             break;

        //             default:
        //             Role useRole = roleRepo.findByName(ERole.USER)
        //             .orElseThrow(() -> new RuntimeException("message"));
        //             roles.add(useRole);

        //         }
        //     });
        // }