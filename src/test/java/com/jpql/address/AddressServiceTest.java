package com.jpql.address;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.jpql.Repository.UserRepo;
import com.jpql.Repository.address.AddressRepo;
import com.jpql.dto.exceptions.AddressNotFoundException;
import com.jpql.service.address.AddressService;
import com.jpql.usermodel.Address;
import com.jpql.usermodel.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private AddressRepo addressRepo;

    @InjectMocks
    private AddressService addressService;

    private User user;

    private Address address;


    @BeforeEach
    void setUp(){
        user = User.builder()
        .email("email")
        .fullName("Rainhard")
        .password("password")
        .address(address)
        .build();
    }


    //This test has passed
    @DisplayName("Add Address Service")
    @Test
    void addAddress(){
        User user1 = new User();
        user1.setUserId(1L);
        user1.setEmail("rainhard@gmail.com");
        user1.setLocked(false);
        user1.setAddress(address);
        Address address = new Address(1L, "street", "houseNumber", "Urban_village",
        "district", "zipCode", "phoneNumber", user1);
        when(userRepo.save(user1)).thenReturn(user1);
        User savedUser = addressService.addAddress(address, user1);
        System.out.println(savedUser.toString());

        verify(userRepo, Mockito.times(1)).save(user1);
        Assertions.assertNotNull(user1);
    }


    // @DisplayName("Update User Address")
    // @Test
    // void editAddress_andReturnAddress_whenAddressIsChanged(){
    //     User user = new User();
    //     user.setUserId(1L);
    //     user.setAddress(address);
    //     user.setEmail("rainhard@gmail.com");
    //     Address address = new Address(1L, "street", "houseNumber", "Urban_village",
    //     "district", "zipCode", "phoneNumber", user);
        
    //     when(userRepo.save(user)).thenReturn(user);
    //     User savedUser = addressService.editAddress(address.getAddressId(), user, address);
    //     Assertions.assertNotNull(savedUser);
    //     verify(userRepo, times(1)).save(user);
    // }


    @DisplayName("Update User AddressTwo")
    @Test
    void editAddress_andReturnAddress_whenAddressIsChangedTwo(){
        User user = new User();
        user.setUserId(1L);
        user.setAddress(address);
        user.setEmail("rainhard@gmail.com");
        Long id = 1L;
        Address address = new Address(id, "street", "houseNumber", "Urban_village",
        "district", "zipCode", "phoneNumber", user);

        Optional<Address> addressFindId = addressRepo.findById(id);

        address.setDistrict("New District");
        user.setAddress(address);
        address.setUser(user);
        when(userRepo.save(user)).thenReturn(user);
        addressService.editAddress(id, user, address);
    }

    
}
