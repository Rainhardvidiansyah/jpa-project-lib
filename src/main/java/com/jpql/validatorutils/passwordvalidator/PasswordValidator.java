package com.jpql.validatorutils.passwordvalidator;

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
@Constraint(validatedBy = PasswordConstraintValidator.class)
//@Constraint(validatedBy = EmailConstraintValidator.class)
public @interface PasswordValidator {

    String message();
    public Class<?>[] groups() default{};
    public Class<? extends Payload>[] payload() default{};
    
}
