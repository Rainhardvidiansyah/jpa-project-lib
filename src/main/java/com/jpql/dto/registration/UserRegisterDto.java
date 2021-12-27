package com.jpql.dto.registration;

import com.jpql.validatorutils.emailvalidator.EmailValidator;
import com.jpql.validatorutils.passwordvalidator.PasswordValidator;
import com.jpql.validatorutils.usernamevalidator.UsernameValidator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@PasswordValidator(message = "Password Tidak Sama... ")
@UsernameValidator(message = "Nama Pengguna Tidak Boleh Kosong...")
public class UserRegisterDto {


    private String nameofuser;
    @EmailValidator(message = "Email Sudah Terdaftar...")
    private String email;

    private String password;

    private String matchPassword;
    
}
