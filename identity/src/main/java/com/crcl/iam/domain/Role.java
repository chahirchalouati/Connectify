package com.crcl.iam.domain;

import com.crcl.iam.validators.annotations.UniqueRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@Document("roles")
@Data
@NoArgsConstructor
public class Role {

    @Id
    private String id;

    @Field("enabled")
    private boolean enabled = true;

    @Field("name")
    @UniqueRole
    private String name;

    @Field("permissions")
    private Set<Permission> permissions = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }
}
