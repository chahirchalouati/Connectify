package com.crcl.iam.servcies.impl;

import com.crcl.iam.domain.Permission;
import com.crcl.iam.dto.PermissionDto;
import com.crcl.iam.mappers.ReactivePermissionMapper;
import com.crcl.iam.repositories.PermissionRepository;
import com.crcl.iam.servcies.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final ReactivePermissionMapper permissionMapper;

    @Override
    public Mono<PermissionDto> save(PermissionDto userDto) {

        return permissionMapper.toReactiveEntity(userDto)
                .flatMap(permissionRepository::save)
                .flatMap(permissionMapper::toReactiveDto);
    }

    @Override
    public Flux<PermissionDto> saveAll(List<PermissionDto> entitiesDto) {
        Flux<Permission> permissions = permissionRepository.saveAll(permissionMapper.mapToReactiveEntity(Flux.fromIterable(entitiesDto)));
        return permissionMapper.mapToReactiveDto(permissions);
    }

    @Override
    public Mono<Void> deleteById(String userId) {
        return permissionRepository.deleteById(userId);
    }

    @Override
    public Mono<PermissionDto> findById(String userId) {
        return permissionRepository.findById(userId).flatMap(permissionMapper::toReactiveDto);
    }

    @Override
    public Flux<PermissionDto> findAll() {
        return permissionRepository.findAll().flatMap(permissionMapper::toReactiveDto);
    }

    @Override
    public Mono<Page<PermissionDto>> findAll(Pageable pageable) {
        return Mono.empty();
    }

    @Override
    public Mono<PermissionDto> update(PermissionDto userDto, String userId) {
        return Mono.empty();
    }
}
