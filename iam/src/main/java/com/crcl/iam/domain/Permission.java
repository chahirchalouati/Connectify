package com.crcl.iam.domain;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document("permissions")
@Data
@Accessors(chain = true)
public class Permission {
    @Id
    private String id;
    private boolean enabled = true;
    @Indexed(unique = true, background = true)
    private String name;

    public Permission(String name) {
        this.name = name;
    }
}
