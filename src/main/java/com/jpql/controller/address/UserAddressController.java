package com.jpql.controller.address;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jpql.Repository.UserRepo;
import com.jpql.dto.address.AddressDto;
import com.jpql.dto.errorhandling.ResponseMessage;
import com.jpql.service.address.AddressService;
import com.jpql.usermodel.Address;
import com.jpql.usermodel.User;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserAddressController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAddressController.class);

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRepo userRepo;

    private ModelMapper modelMapper;

    @Autowired
    private HttpServletRequest request;

    private ResponseMessage responseMessage = new ResponseMessage<>(true, "Success");
    private ResponseMessage exceptionMessage = new ResponseMessage<>(false, "Trouble is happening");

    @PostMapping("/save/address")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DEV')")
    public ResponseEntity<?> saveAddress(@RequestBody Address address){
        String email = request.getUserPrincipal().getName();
        User user = userRepo.findByEmail(email).orElse(null);
        addressService.addAddress(address, user);
        LOGGER.info("User {} has inputed address {}", user.getFullName(), address);
        return new ResponseEntity<>(
            responseMessage, HttpStatus.OK);
    }

    @PutMapping("/update/address/{addressid}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('DEV')")
    public ResponseEntity<?> updateUserAddress(@PathVariable("addressid") Long addressId, 
    @RequestBody Address address){
        if(addressId == null){
            return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
        }
        String email = request.getUserPrincipal().getName();
        User user = userRepo.findByEmail(email).orElse(null);
        addressService.editAddress(addressId, user, address);
        
        LOGGER.info("{} just updated the address to {}", user.getFullName(), address);
        LOGGER.info("Id {} from address", address.getAddressId());
        return new ResponseEntity<>(
            responseMessage, HttpStatus.OK);
    }


    @GetMapping(value = "/myprofile")
    @ResponseBody
    public ResponseEntity<?> getUserAddressByUser(@RequestParam Long addressid){
        String email = request.getUserPrincipal().getName();
        User user = userRepo.findByEmail(email).orElse(null);
        if(addressid == null || addressid != user.getAddress().getAddressId()){
            return new ResponseEntity<>(new ResponseMessage<>(false, "Hello " + user.getFullName() +" Your address not Found!"), HttpStatus.BAD_REQUEST);
        }
        addressService.userAddress(user, addressid);
        LOGGER.info("{} just hit this end-point, and the addressid is {}", user.getFullName(), addressid);
        return new ResponseEntity<>(new ResponseMessage<>(true, "message", user.getAddress()), HttpStatus.OK);
    }

    



}
