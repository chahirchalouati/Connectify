package com.crcl.iam.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ContactInformation {

    @Pattern(
            regexp = "^\\+\\s?\\d{1,2}\\s?\\d{9,12}$",
            message = "Invalid phone number format"
    )
    private String phone;

    @Pattern(
            regexp = "^\\+\\s?\\d{1,2}\\s?\\d{9,12}$",
            message = "Invalid mobile number format"
    )
    private String mobile;

    @Email(message = "Invalid email address")
    private String email;
}
