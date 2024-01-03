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

/**
 * The PermissionServiceImpl class is an implementation of the PermissionService interface.
 * It provides methods for CRUD operations on Permission entities using reactive programming.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final ReactivePermissionMapper permissionMapper;

    /**
     * Saves a PermissionDto object.
     *
     * @param userDto The PermissionDto object to save.
     * @return A Mono emitting the saved PermissionDto object.
     */
    @Override
    public Mono<PermissionDto> save(PermissionDto userDto) {

        return permissionMapper.toReactiveEntity(userDto)
                .flatMap(permissionRepository::save)
                .flatMap(permissionMapper::toReactiveDto);
    }

    /**
     * Saves a list of PermissionDto entities.
     *
     * @param entitiesDto the list of PermissionDto entities to be saved
     * @return a Flux emitting the saved PermissionDto entities
     */
    @Override
    public Flux<PermissionDto> saveAll(List<PermissionDto> entitiesDto) {
        Flux<Permission> permissions = permissionRepository.saveAll(permissionMapper.mapToReactiveEntity(Flux.fromIterable(entitiesDto)));
        return permissionMapper.mapToReactiveDto(permissions);
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param userId the ID of the entity to be deleted
     * @return a Mono representing the completion of the deletion process
     */
    @Override
    public Mono<Void> deleteById(String userId) {
        return permissionRepository.deleteById(userId);
    }

    /**
     * Finds a permission entity by the specified userId.
     *
     * @param userId the ID of the permission entity to find
     * @return a Mono emitting the permission DTO if found, or completing empty if not found
     */
    @Override
    public Mono<PermissionDto> findById(String userId) {
        return permissionRepository.findById(userId).flatMap(permissionMapper::toReactiveDto);
    }

    /**
     * Finds all permission entities and maps them to PermissionDto objects using the permissionMapper.
     *
     * @return a Flux emitting PermissionDto objects representing all the permissions.
     */
    @Override
    public Flux<PermissionDto> findAll() {
        return permissionRepository.findAll().flatMap(permissionMapper::toReactiveDto);
    }

    /**
     * Finds all entities in a paginated manner.
     *
     * @param pageable the pagination information
     * @return a Mono emitting the Page of PermissionDto
     */
    @Override
    public Mono<Page<PermissionDto>> findAll(Pageable pageable) {
        return Mono.empty();
    }

    /**
     * Updates an entity with the given entityDto and entityId.
     *
     * @param userDto The entityDto containing the updated data.
     * @param userId  The id of the entity to be updated.
     * @return A Mono emitting the updated entityDto.
     */
    @Override
    public Mono<PermissionDto> update(PermissionDto userDto, String userId) {
        return Mono.empty();
    }
}
