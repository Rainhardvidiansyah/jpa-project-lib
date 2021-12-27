package com.jpql.validatorutils.passwordvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.jpql.dto.registration.UserRegisterDto;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordValidator, Object>{

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext arg1) {
        UserRegisterDto registrationDto = (UserRegisterDto) o;
        return registrationDto.getPassword().equals(registrationDto.getMatchPassword());
    }
    
}
