package com.jpql.dto.errorhandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ResponseMessage<T> {

    private boolean status;
    private String message;
    private T newType;
    

    public ResponseMessage(boolean status, String message){
        this.status = status;
        this.message = message;
    }

}
