package com.jpql.controller.registration;



import javax.validation.Valid;

import com.jpql.Repository.role.RoleRepo;
import com.jpql.dto.errorhandling.ResponseData;
import com.jpql.dto.registration.RegistrationRequest;
import com.jpql.service.UserService;
import com.jpql.service.verificationtoken.VerificationTokenService;
import com.jpql.usermodel.User;
import com.jpql.usermodel.VerificationToken;
import com.jpql.utilities.ErrorUtils;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);


    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private UserService userService;

    @Autowired
    VerificationTokenService tokenService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/registration")    
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest request, Errors errors){
        ResponseData<RegistrationRequest> responseData = new ResponseData();
        if(errors.hasErrors()){
            responseData.setStatus(false);
            responseData.setMessages(ErrorUtils.err(errors));
            responseData.setPayload(null);
            return ResponseEntity.badRequest().body(responseData);
        }
        
        User user = modelMapper.map(request, User.class);
        request = modelMapper.map(user, RegistrationRequest.class);
        userService.registration(user);
        responseData.setStatus(true);
        responseData.setPayload(modelMapper.map(user, RegistrationRequest.class));
        //return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok().body("Registrasi Diterima");
    }


    /**
     MY PURPOSE HERE:
     1. FIND TOKEN SO THAT THE RETURN VALUE IS TOKEN EXIST AND ACCEPTED
     2. USER WITH USERNAME, FOR EXAMPLE, "RAINHARD" CAN LOGIN INTO APP
     3.
     */
    @GetMapping("/confirmation/{token}")
    public String confirmToken(@RequestParam(value = "token", required = false) String token){
        VerificationToken vToken = tokenService.findToken(token);
        VerificationToken vtoken = userService.confirmationToken(vToken.getToken());
        return vToken + " ada";
    }


    /* 
    INI MASIH PERCOBAAN: BAGAIMANA JIKA DITINJAU DENGAN ID DARI TIAP-TIAP ENTITY
     */
    @GetMapping("/gettoken")
    public VerificationToken getToken(Long id){
        return tokenService.getToken(id);
        
    }

    @GetMapping("/test")
    public String testing(){
        return "Haloooo";
    }

    
    
}
