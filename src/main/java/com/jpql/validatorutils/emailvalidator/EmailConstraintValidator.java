package com.jpql.validatorutils.emailvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jpql.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;

public class EmailConstraintValidator implements ConstraintValidator<EmailValidator, String>{

    @Autowired
    private UserRepo userRepo;
    
    @Override
    public boolean isValid(String email, ConstraintValidatorContext arg1) {
        return false;
    }

  

    
}
