package com.crcl.iam.domain;

import com.crcl.iam.validators.annotations.UniquePermission;
import com.crcl.iam.validators.annotations.UniqueRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("permissions")
@Data
@NoArgsConstructor
public class Permission {

    @Id
    private String id;

    @Field("enabled")
    private boolean enabled = true;

    @Field("name")
    @UniquePermission
    private String name;

    public Permission(String name) {
        this.name = name;
    }
}
