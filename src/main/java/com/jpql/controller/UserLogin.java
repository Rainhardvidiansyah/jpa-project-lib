package com.jpql.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.jpql.Repository.UserRepo;
import com.jpql.configuration.jwt.JwtUtils;
import com.jpql.dto.LoginDto;
import com.jpql.dto.jwt.JwtResponse;
import com.jpql.usermodel.UserDetailsImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")

public class UserLogin {

	private static final Logger log = LoggerFactory.getLogger(UserLogin.class);
    
    @Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepo userRepo;

	@Autowired
	JwtUtils jwtUtils;



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		if(authentication.getPrincipal() == null){
			return new ResponseEntity<>("You have not registered in this application", HttpStatus.BAD_REQUEST);
		}
		String jwt = jwtUtils.generateJwtToken(authentication);
		log.info("User just logged in: {}", loginRequest.getEmail());
		
		UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = user.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
				log.info("Role for User: {}", roles);
		return ResponseEntity.ok(new JwtResponse(jwt, "Bearer", user.getUserId(), 
		user.getFullName(), user.getEmail(), roles));
		
		
	}
	

    
}
