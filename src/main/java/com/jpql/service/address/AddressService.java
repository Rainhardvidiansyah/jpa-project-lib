package com.jpql.service.address;

import java.util.List;

import com.jpql.Repository.UserRepo;
import com.jpql.Repository.address.AddressRepo;
import com.jpql.usermodel.Address;
import com.jpql.usermodel.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    /*
    THIS CLASS SHOULD CONTAIN:
    1. USER ADD HIS ADDRESS
    2, USER FIND HIS ADDRESS AND FIND HIS SECONDARY ADDRESS
    */

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;
    

    public Address addNewAddress(Address address){
        User user = new User();
        user.setAddress(address);
        return addressRepo.save(address);
    }

    public List<Address> showAllMyAddress(){
        return addressRepo.findAll();
    }


    
}
