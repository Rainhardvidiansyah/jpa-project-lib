package com.jpql.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ErrorUtils {

    public static List<String> err(Errors errors){
        List<String> messages = new ArrayList<String>();
        for(ObjectError objectError : errors.getAllErrors()){
            messages.add(objectError.getDefaultMessage());
        }
        return messages;
    }
    
    
}
