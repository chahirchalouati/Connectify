package com.crcl.iam.dto;

import lombok.Data;

@Data
public class PermissionDto {
    private String id;
    private String name;
    private boolean enabled;
}
