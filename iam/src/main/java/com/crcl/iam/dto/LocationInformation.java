package com.crcl.iam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LocationInformation {

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "Country cannot be blank")
    private String country;

    private String street;

    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;
}
