package com.jpql.dto.errorhandling;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor 
@AllArgsConstructor
@Getter
@Setter
public class ResponseData<T> {

    private boolean status;

    private List<String> messages = new ArrayList<>();

    private T payload;

}
