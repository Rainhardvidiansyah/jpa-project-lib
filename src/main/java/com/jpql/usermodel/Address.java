package com.jpql.usermodel;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_address")
@Getter @Setter
public class Address {

    private String street;
    
}
