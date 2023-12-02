package com.crcl.iam.domain;

import com.crcl.iam.validators.annotations.UniqueEmail;
import com.crcl.iam.validators.annotations.UniqueUserName;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Table("users")
@Data
@FieldNameConstants
@NoArgsConstructor
public class User {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    private UUID id = UUID.randomUUID();

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordinal = 1, ordering = Ordering.DESCENDING)
    private UUID timeUUID = Uuids.timeBased();

    @NotBlank
    @Column("first_name")
    private String firstName;

    @NotBlank
    @Column(value = "last_name")
    private String lastName;

    @NotBlank
    @UniqueUserName
    @Column("user_name")
    private String userName;

    @Email
    @UniqueEmail
    @Column("email")
    private String email;

    @NotBlank
    @Column("password")
    private String password;

    @NotBlank
    @Column("avatar")
    private String avatar;

    @NotBlank
    @Column("gender")
    private Gender gender;

    @Column("roles")
    @CassandraType(type = CassandraType.Name.SET, typeArguments = CassandraType.Name.TEXT)
    private Set<Role> roles = new HashSet<>();

    @Column("account_non_expired")
    private boolean isAccountNonExpired = true;

    @Column("enabled")
    private boolean isEnabled = true;

    @Column("credentials_non_expired")
    private boolean isCredentialsNonExpired = true;

    @Column("account_non_locked")
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
