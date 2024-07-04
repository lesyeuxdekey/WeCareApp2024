package com.keylin.WeCare.dto;

import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "This field should not be empty")
    private String name;
    @NotEmpty(message = "This field should not be empty")
    private String city;
    @NotEmpty(message = "This field should not be empty")
    private String postalCode;
    @NotEmpty(message = "This field should not be empty")
    private Double serviceFee;
    @NotEmpty(message = "This field should not be empty")
    private String description;
    @NotEmpty(message = "This field should not be empty")
    private int numChildren;
    @NotEmpty(message = "This field should not be empty")
    private String photoUrl;

}
