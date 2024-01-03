package com.crcl.iam.mappers;

import com.crcl.core.service.generic.ReactiveGenericMapper;
import com.crcl.iam.domain.Role;
import com.crcl.iam.dto.RoleDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ReactiveRoleMapper extends ReactiveGenericMapper<Role, RoleDto> {

}
