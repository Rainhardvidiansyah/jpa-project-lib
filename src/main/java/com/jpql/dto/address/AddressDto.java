package com.jpql.dto.address;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class AddressDto {

    private String street;
    private String houseNumber;
    private String urbanVillage;
    private String district;
    private String zipCode;
    private String phoneNumber;
    
}
