package com.jpql.usermodel;

import com.jpql.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminDb implements CommandLineRunner{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {

        //userRepo.save(new User("DEV", "dev@gmail.com", passwordEncoder.encode("DEVELOPER")));
    }



    
}
