package com.jpql.entities.favourite;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jpql.entities.product.ProductEntity;
import com.jpql.helper.audit.Auditing;
import com.jpql.usermodel.User;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import antlr.collections.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table()
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class UserWishlist extends Auditing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductEntity productEntity;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;




}
