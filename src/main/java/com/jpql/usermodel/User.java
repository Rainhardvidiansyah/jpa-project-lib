package com.jpql.usermodel;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jpql.entities.cart.CartEntity;
import com.jpql.helper.audit.Auditing;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.NoArgsConstructor;


@Entity
@Table(name = "users")
@NoArgsConstructor


public class User extends Auditing implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private long id;

    @Column(name = "user_name", length = 150, nullable = false)
    private String nameofuser;

    @Column(name = "user_email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "password_user", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<VerificationToken> verificationToken;

    private boolean locked;
    private boolean isEnabled;


    /* RELATION TO ONE-TO-ONE
    TO USER ADRESS 
    CATATAN TAMBAHAN: (referencedColumnName = "addressId")
    ITU HARUS BEGITU, KALAU PAKAI "ID" SAJA, NANTI AKAN ERROR
    */
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    /**
    RELATION TO CART_ENTITY
     */
    @OneToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private CartEntity cartEntity;


    public long getId() {
        return id;
    }

    public User(String nameofuser, String email, 
                String password) {
        this.nameofuser = nameofuser;
        this.email = email;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameofuser() {
        return nameofuser;
    }

    public void setNameofuser(String nameofuser) {
        this.nameofuser = nameofuser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {

        this.password = password;
    }
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Set<VerificationToken> getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(Set<VerificationToken> verificationToken) {
        this.verificationToken = verificationToken;
    }
    
    /* 
    Below is User Details Implementation 
    */

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return !isEnabled;
    }

    
    
}
