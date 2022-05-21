package com.jpql.service.address;

import java.util.List;
import java.util.Optional;

import javax.mail.internet.AddressException;
import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;

import com.jpql.Repository.UserRepo;
import com.jpql.Repository.address.AddressRepo;
import com.jpql.dto.errorhandling.ResponseMessage;
import com.jpql.dto.exceptions.AddressNotFoundException;
import com.jpql.dto.exceptions.NotUserAddressException;
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
    

   public User addAddress(Address address, User user){
       user.setAddress(address);
        return user = userRepo.save(user);
   }
   
   public void editAddress(Long id, User user, Address oldAddress){
        Address getAddressId = addressRepo.findById(id)
        .orElseThrow(() -> new AddressNotFoundException("Can't find that Address"));
        
       if(getAddressId.getUser() != user){
           throw new NotUserAddressException("This is not your address!");
       }
        getAddressId.setStreet(oldAddress.getStreet());
        getAddressId.setHouseNumber(oldAddress.getHouseNumber());
        getAddressId.setUrbanVillage(oldAddress.getUrbanVillage());
        getAddressId.setDistrict(oldAddress.getDistrict());
        getAddressId.setZipCode(oldAddress.getZipCode());
        getAddressId.setPhoneNumber(oldAddress.getPhoneNumber());
        getAddressId.setUser(user);
        user.setAddress(getAddressId);
        addressRepo.save(getAddressId);
        // userRepo.save(user);
   }

   public User userAddress(User user, Long id){
       Address address = addressRepo.findById(id).orElseThrow(() -> new AddressNotFoundException("Address Not Found"));
       if(address.getUser() != user){
            throw new RuntimeException("Not Your Address");
       }
       return userRepo.findAddress(address);
   }

   

  

    
}
