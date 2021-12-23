package com.jpql.validatorutils.usernamevalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jpql.dto.registration.RegistrationDto;

public class UsernameConstraintValidator implements ConstraintValidator<UsernameValidator, Object>{

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        RegistrationDto registrationDto = (RegistrationDto) o;
        
        return !registrationDto.getNameofuser().isEmpty();
    }


    
}
