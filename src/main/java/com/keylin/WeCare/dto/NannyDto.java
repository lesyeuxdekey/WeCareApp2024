package com.keylin.WeCare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NannyDto {
	private Long id;
	private String name;
	private int age;
	private String city;
	private String postalCode;
	private Double serviceFee;
	private String description;
	private int yearsOfExperience;
	private String photoUrl;
}
