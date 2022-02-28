package com.jpql.usermodel;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jpql.entities.cart.CartItems;
import com.jpql.entities.favourite.UserWishlist;
import com.jpql.helper.audit.Auditing;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor


public class User extends Auditing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "user_name", length = 150, nullable = false)
    private String fullName;

    @Column(name = "user_email", length = 150, nullable = false, unique = true)
    private String email;

    @Column(name = "password_user", nullable = false)
    private String password;

    // @OneToMany(mappedBy = "user")
    // private Set<VerificationToken> verificationToken;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user_tbl",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List <Role> role;

    private boolean locked;
    private boolean isEnabled;

   

    /* RELATION TO ONE-TO-ONE
    TO USER ADRESS 
    CATATAN TAMBAHAN: (referencedColumnName = "addressId")
    ITU HARUS BEGITU, KALAU PAKAI "ID" SAJA, NANTI AKAN ERROR
    */
    @OneToOne(mappedBy = "user")
    //@JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    @OneToOne(mappedBy = "user")
    private UserWishlist wishlist;

    @OneToMany(mappedBy = "user")
    private Set<CartItems> cartItems;

    public User(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public User(String fullName, String email, String password, List<Role> role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }







    
}
