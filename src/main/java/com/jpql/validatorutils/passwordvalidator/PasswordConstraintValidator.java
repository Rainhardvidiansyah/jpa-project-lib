package com.jpql.validatorutils.passwordvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jpql.dto.registration.RegistrationRequest;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordValidator, Object>{

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext arg1) {
        RegistrationRequest request = (RegistrationRequest) o;
        return request.getPassword().equals(request.getMatchPassword());
    }
    
}
