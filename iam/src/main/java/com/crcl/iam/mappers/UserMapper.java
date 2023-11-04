package com.crcl.iam.mappers;

import com.crcl.iam.domain.User;
import com.crcl.iam.dto.UserDto;
import com.crcl.core.service.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {RoleMapper.class, PermissionMapper.class}
)
public interface UserMapper extends GenericMapper<User, UserDto> {

}
