package com.crcl.iam.dto;

import com.crcl.iam.domain.Gender;
import com.crcl.iam.domain.Role;
import com.crcl.iam.validators.annotations.UniqueEmail;
import com.crcl.iam.validators.annotations.UniqueUserName;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UserDto {
    private String id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @UniqueUserName
    private String username;
    @UniqueEmail
    @Email
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String avatar;
    private Gender gender;
    private Set<Role> roles = new HashSet<>();
    private boolean isAccountNonExpired = true;
    private boolean isEnabled = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonLocked = true;
}
