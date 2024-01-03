package com.crcl.iam.servcies;

import com.crcl.core.service.generic.ReactiveGenericService;
import com.crcl.iam.dto.UserDto;

/**
 * UserService is an interface that extends ReactiveGenericService and provides methods for CRUD operations on UserDto objects.
 *
 * @param <UserDto> the type of the entity DTO (UserDto)
 * @param <String>  the type of the entity ID (String)
 */
public interface UserService extends ReactiveGenericService<UserDto, String> {

}
