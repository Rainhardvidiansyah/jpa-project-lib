package com.jpql.usermodel;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails{

    private Long userId;

    private String fullName;

    public String email;

    @JsonIgnore
    public String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long userId, String fullName, String email, String password,
            Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user){
        List<GrantedAuthority> authorities = user.getRole().stream()
        .map(a -> new SimpleGrantedAuthority(a.getName().name()))
        .collect(Collectors.toList());

        return new UserDetailsImpl(user.getUserId(), 
        user.getFullName(), user.getEmail(), 
        user.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    

    

    


    
}
