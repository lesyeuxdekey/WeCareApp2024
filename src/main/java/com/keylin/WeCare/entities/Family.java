package com.keylin.WeCare.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Family")
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String email;
    private int telephone;
    private String city;
    @Column(name = "postalcode")
    private String postalCode;
    private String address;
    @Column(name = "servicefee")
    private Double serviceFee;
    private String description;
    @Column(name = "numchildren")
    private int numChildren;
    @Column(name = "photourl")
    private String photoUrl;

}
