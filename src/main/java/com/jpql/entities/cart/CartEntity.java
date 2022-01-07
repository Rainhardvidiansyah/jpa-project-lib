package com.jpql.entities.cart;


import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jpql.helper.audit.Auditing;
import com.jpql.usermodel.User;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter

@SQLDelete(sql = "update CartEntity set ordered = true where cartId = ?")
@Where(clause = "ordered = true, deleted = true")
public class CartEntity extends Auditing{

    /**
     THIS CLASS CONTAINS:
     1. ID
     2. QUANTITY OF PRODUCT
     3. RELATIONSHIP BETWEEN CARTENTITY and USER_ENTITY 
     4. RELATIONSHIP BETWEEN CART_ENTITY and PRODUCT_ENTITY
     5. SOFT-DELETE IF PRODUCT GOT ORDERED
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    private boolean ordered = Boolean.FALSE;

    @OneToOne(mappedBy = "cartEntity")
    private User user;

    @OneToMany(mappedBy = "cartEntity")
    private Set<CartItems> cartItems;


    
}
