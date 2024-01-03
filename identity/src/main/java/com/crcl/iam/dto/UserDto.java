package com.crcl.iam.dto;

import com.crcl.iam.domain.Gender;
import com.crcl.iam.domain.Permission;
import com.crcl.iam.domain.Role;
import com.crcl.iam.validators.annotations.UniqueEmail;
import com.crcl.iam.validators.annotations.UniqueUserName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
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
    private String userName;
    @UniqueEmail
    @Email
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String confirmPassword;
    private String avatar;
    private String phone;
    private Gender gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDate birthDate;
    private Set<Role> roles = new HashSet<>();
    private boolean isAccountNonExpired = true;
    private boolean isEnabled = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isAccountNonLocked = true;

    public static void main(String[] args) throws JsonProcessingException {
        // Create an example instance of UserDto
        final UserDto userDto = new UserDto()
                .setId("123")
                .setFirstName("John")
                .setLastName("Doe")
                .setUserName("john_doe")
                .setEmail("john.doe@example.com")
                .setPassword("password")
                .setConfirmPassword("password")
                .setAvatar("avatar_url")
                .setPhone("1234567890")
                .setGender(Gender.MALE)
                .setBirthDate(LocalDate.of(1990, 1, 1));

        // Add roles to the userDto
        final Role adminRole = new Role("ROLE_ADMIN");
        adminRole.getPermissions().add(new Permission("READ_PERMISSION"));
        adminRole.getPermissions().add(new Permission("WRITE_PERMISSION"));

        userDto.getRoles().add(adminRole);

        // Print the JSON representation manually
        final ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT);

        System.out.println(objectMapper.writeValueAsString(userDto));
    }
}
