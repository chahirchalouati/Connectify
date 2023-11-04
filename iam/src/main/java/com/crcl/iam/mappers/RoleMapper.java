package com.crcl.iam.mappers;

import com.crcl.iam.domain.Role;
import com.crcl.iam.dto.RoleDto;
import com.crcl.core.service.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper extends GenericMapper<Role, RoleDto> {

}
