package com.keylin.WeCare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilyDto {
    private Long id;
    private String name;
    private String city;
    private String postalCode;
    private Double serviceFee;
    private String description;
    private int numChildren;
    private String photoUrl;

}
