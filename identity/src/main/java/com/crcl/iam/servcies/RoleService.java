package com.crcl.iam.servcies;

import com.crcl.core.service.generic.ReactiveGenericService;
import com.crcl.iam.dto.RoleDto;

/**
 * The RoleService interface extends the ReactiveGenericService interface and provides
 * methods for CRUD operations on RoleDto entities using reactive programming.
 *
 * @param <RoleDto> the type of the RoleDto entity
 * @param <String>  the type of the RoleDto ID
 */
public interface RoleService extends ReactiveGenericService<RoleDto, String> {

}
