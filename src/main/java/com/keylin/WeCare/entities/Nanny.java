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
@Table(name = "Nanny")
public class Nanny {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String name;
	private int age;
	private String email;
	private int telephone;
	private String city;
	@Column(name = "postalcode")
	private String postalCode;
	private String address;
	@Column(name = "servicefee")
	private Float serviceFee;
	private String description;
	@Column(name = "yearsofexperience")
	private int yearsOfExperience;
	@Column(name = "photourl")
	private String photoUrl;

}
