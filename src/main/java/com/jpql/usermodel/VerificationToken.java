package com.jpql.usermodel;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "verification_token")
@Getter @Setter
@NoArgsConstructor
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    public String token;
    
    private LocalDateTime createdAt;

    private LocalDateTime confirmedAt;

    private LocalDateTime expiresAt;

    // @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    // @JoinColumn(name = "user_id")
    // private User user;

    // public VerificationToken(User user) {
    //     this.user = user;
    //     createdAt = LocalDateTime.now();
    //     token = UUID.randomUUID().toString();
    // }





    
}
