package com.crcl.iam.mappers;

import com.crcl.iam.domain.GramifyPermission;
import com.crcl.iam.dto.PermissionDto;
import com.crcl.core.service.generic.GenericMapper;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PermissionMapper extends GenericMapper<GramifyPermission, PermissionDto> {

}
