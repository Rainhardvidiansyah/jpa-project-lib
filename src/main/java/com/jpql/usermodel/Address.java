package com.jpql.usermodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_address")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(name = "nama_jalan", length = 300)
    private String street;

    @Column(name = "Nomor_rumah", length = 225)
    private String houseNumber;

    @Column(name = "kelurahan", length = 100)
    private String urbanVillage;

    @Column(name = "kecamatan", length = 100)
    private String district;

    @Column(name = "kodepos", length = 50)
    private String zipCode;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    // @OneToOne
    // @JoinColumn(name = "user_id", referencedColumnName = "user_id")

    
    @JsonBackReference
    @OneToOne(mappedBy = "address")
    private User user;

    public Address(Long addressId, String street, String houseNumber, String urbanVillage, String district,
            String zipCode, String phoneNumber) {
        this.addressId = addressId;
        this.street = street;
        this.houseNumber = houseNumber;
        this.urbanVillage = urbanVillage;
        this.district = district;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    

    
    
}
