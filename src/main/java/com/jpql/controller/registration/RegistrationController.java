package com.jpql.controller.registration;

import javax.validation.Valid;

import com.jpql.dto.errorhandling.ResponseData;
import com.jpql.dto.registration.UserRegisterDto;
import com.jpql.service.UserService;
import com.jpql.usermodel.User;
import com.jpql.utilities.ErrorUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registration")    
    public ResponseEntity<?> registerUser(@Valid UserRegisterDto userRegisterDto, Errors errors){
        ResponseData<UserRegisterDto> responseData = new ResponseData();
        if(errors.hasErrors()){
            responseData.setStatus(false);
            responseData.setMessages(ErrorUtils.err(errors));
            responseData.setPayload(null);
            return ResponseEntity.badRequest().body(responseData);
        }
        User user = modelMapper.map(userRegisterDto, User.class);
        userService.registration(user);
        responseData.setStatus(true);
        responseData.setPayload(modelMapper.map(user, UserRegisterDto.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
    
}
