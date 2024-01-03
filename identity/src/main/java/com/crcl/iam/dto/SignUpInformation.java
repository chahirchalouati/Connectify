package com.crcl.iam.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpInformation {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).*$",
            message = "Password must be strong with at least one digit, one lowercase and one uppercase letter, and one special character"
    )
    private String password;

    @NotBlank(message = "Confirm Password cannot be blank")
    @Size(min = 6, message = "Confirm Password must be at least 6 characters")
    private String confirmPassword;
}
