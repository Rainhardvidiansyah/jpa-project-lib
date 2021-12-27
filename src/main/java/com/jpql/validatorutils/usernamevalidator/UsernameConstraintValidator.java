package com.jpql.validatorutils.usernamevalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jpql.dto.registration.UserRegisterDto;

public class UsernameConstraintValidator implements ConstraintValidator<UsernameValidator, Object>{

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        UserRegisterDto registrationDto = (UserRegisterDto) o;
        
        return !registrationDto.getNameofuser().isEmpty();
    }


    
}
