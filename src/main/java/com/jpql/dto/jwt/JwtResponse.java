package com.jpql.dto.jwt;

import java.util.List;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class JwtResponse {

    private String token;

    private String type = "Bearer";

    private Long id;

    private String fullName;

    private String email;

    private List<String> roles;

    public JwtResponse(String accessToken, String type, Long id, String fullName, String email) {
        this.token = accessToken;
        this.type = type;
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

   public void setFullName(String fullName) {
       this.fullName = fullName;
   }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    


    
}
