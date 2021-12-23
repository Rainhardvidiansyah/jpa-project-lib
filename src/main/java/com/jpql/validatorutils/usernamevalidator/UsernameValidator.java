package com.jpql.validatorutils.usernamevalidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = UsernameConstraintValidator.class)

public @interface UsernameValidator {
    String message();
    public Class<?>[] groups() default{};
    public Class<? extends Payload>[] payload() default{};
    
}
