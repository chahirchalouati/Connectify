package com.crcl.iam.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoleDto {
    private String id;
    private String name;
    private boolean enabled;
    private Set<PermissionDto> permissions = new HashSet<>();
}
