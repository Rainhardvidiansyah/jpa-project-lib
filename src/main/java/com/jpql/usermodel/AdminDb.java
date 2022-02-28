package com.jpql.usermodel;

import java.util.Arrays;
import java.util.List;

import com.jpql.Repository.UserRepo;
import com.jpql.Repository.role.RoleRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminDb implements CommandLineRunner{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;




    @Override
    public void run(String... args) throws Exception {
        // userRepo.saveAll(Arrays.asList(
        //     new User("USER", "user@gmail.com", passwordEncoder.encode("user")),
        //     new User("ADMIN", "admin@gmail.com", passwordEncoder.encode("admin")),
        //     new User("DEV", "dev@gmail.com", passwordEncoder.encode("dev"))
        // ));
    }



    
}
