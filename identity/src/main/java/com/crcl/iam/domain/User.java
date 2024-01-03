package com.crcl.iam.domain;

import com.crcl.iam.validators.annotations.UniqueEmail;
import com.crcl.iam.validators.annotations.UniqueUserName;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Document("users")
@Data
@FieldNameConstants
@NoArgsConstructor
public class User {
    @Id
    private String id;

    @NotBlank
    @Field("first_name")
    private String firstName;

    @NotBlank
    @Field("last_name")
    private String lastName;

    @NotBlank
    @UniqueUserName
    @Field("user_name")
    private String userName;

    @Email
    @UniqueEmail
    @Field("email")
    private String email;

    @NotBlank
    @Field("password")
    private String password;

    @NotBlank
    @Field("birthDate")
    private LocalDate birthDate;

    @NotBlank
    @Field("avatar")
    private String avatar;

    @NotBlank
    @Field("gender")
    private Gender gender;

    @Field("roles")
    private Set<Role> roles = new HashSet<>();

    @Field("account_non_expired")
    private boolean isAccountNonExpired = true;

    @Field("enabled")
    private boolean isEnabled = true;

    @Field("credentials_non_expired")
    private boolean isCredentialsNonExpired = true;

    @Field("account_non_locked")
    private boolean isAccountNonLocked = true;

    public boolean isAdmin() {
        return this.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }

    public boolean isSuperAdmin() {
        return this.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_SUPER_ADMIN"));
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream()
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
