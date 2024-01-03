package com.crcl.iam.mappers;

import com.crcl.core.service.generic.ReactiveGenericMapper;
import com.crcl.iam.domain.User;
import com.crcl.iam.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReactiveUserMapper extends ReactiveGenericMapper<User, UserDto> {

}
