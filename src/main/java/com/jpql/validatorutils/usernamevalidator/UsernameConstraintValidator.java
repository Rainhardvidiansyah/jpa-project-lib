package com.jpql.validatorutils.usernamevalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jpql.dto.registration.RegistrationRequest;

public class UsernameConstraintValidator implements ConstraintValidator<UsernameValidator, Object>{

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        RegistrationRequest request = (RegistrationRequest) o;
        
        return !request.getFullName().isEmpty();
    }


    
}
