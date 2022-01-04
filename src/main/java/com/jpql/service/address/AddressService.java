package com.jpql.service.address;

import java.util.List;

import com.jpql.Repository.address.AddressRepo;
import com.jpql.usermodel.Address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    public Address addNewAddress(Address address){
        return addressRepo.save(address);
    }

    public List<Address> showAllMyAddress(){
        return addressRepo.findAll();
    }


    
}
