package com.crcl.iam.mappers;

import com.crcl.core.service.generic.ReactiveGenericMapper;
import com.crcl.iam.domain.Permission;
import com.crcl.iam.dto.PermissionDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ReactivePermissionMapper extends ReactiveGenericMapper<Permission, PermissionDto> {

}
