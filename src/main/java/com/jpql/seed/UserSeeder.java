package com.jpql.seed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jpql.Repository.UserRepo;
import com.jpql.Repository.role.RoleRepo;
import com.jpql.service.email.EmailService;
import com.jpql.usermodel.ERole;
import com.jpql.usermodel.Role;
import com.jpql.usermodel.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class UserSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(UserSeeder.class);

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // Role roleUSer = new Role(ERole.ROLE_USER);
        // Role roleAdmin = new Role(ERole.ROLE_ADMIN);
        // Role roleDev = new Role(ERole.ROLE_DEV);
        // roleRepo.saveAll(List.of(
        //     roleUSer, roleAdmin, roleDev
        // ));

        // List<Role> roleForRainhard = new ArrayList<>();
        // roleForRainhard.add(roleUSer);
        // roleForRainhard.add(roleAdmin);

        // List<Role> roleForUser = new ArrayList<>();
        // roleForUser.add(roleUSer);

        // User rainhard = new User("Rainhard as Admin and User", "rainhard@gmail.com", passwordEncoder.encode("rainhard"));
        // rainhard.setRole(roleForRainhard);
        // userRepo.save(rainhard);

        // User user = new User("Rainhard as User", "rainhardvidians@gmail.com", passwordEncoder.encode("user"));
        // user.setRole(roleForUser);
        // userRepo.save(user);

        // User gofur = new User
        // ("Baldatun Gofur as User", "baldatungofur@gmail.com", passwordEncoder.encode("gofur"));
        // gofur.setRole(roleForUser);
        // userRepo.save(gofur);

        // log.info("Saved User: {}", rainhard);

        //Send email to user woh just logged-in is working!
        //emailService.sendEmail(user.getEmail(), "Baru masuk ke cengek bandung", "Ada", new Date());
    }
    
}
