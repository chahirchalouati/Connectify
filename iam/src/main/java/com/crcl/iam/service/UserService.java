package com.crcl.iam.service;

import com.crcl.iam.dto.CreateUserRequest;
import com.crcl.iam.dto.UserDto;
import com.crcl.core.service.generic.GenericService;

import java.util.Set;

public interface UserService extends GenericService<UserDto, String> {

    UserDto findByUsername(String username);

    UserDto save(CreateUserRequest request);

    UserDto getCurrentUser();

    Set<UserDto> findByUserNames(Set<String> userNames);
}

