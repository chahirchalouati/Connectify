package com.crcl.iam.servcies;

import com.crcl.core.service.generic.ReactiveGenericService;
import com.crcl.iam.dto.PermissionDto;

/**
 * The PermissionService interface extends the ReactiveGenericService interface for CRUD operations on PermissionDto entities.
 * It provides methods to save, delete, find, and update permission entities.
 *
 * @param <PermissionDto> the type of the permission DTO
 * @param <String>        the type of the permission ID
 */
public interface PermissionService extends ReactiveGenericService<PermissionDto, String> {

}
