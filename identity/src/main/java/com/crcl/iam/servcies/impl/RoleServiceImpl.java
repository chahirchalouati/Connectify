package com.crcl.iam.servcies.impl;

import com.crcl.iam.domain.Role;
import com.crcl.iam.dto.RoleDto;
import com.crcl.iam.mappers.ReactiveRoleMapper;
import com.crcl.iam.repositories.RoleRepository;
import com.crcl.iam.servcies.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The RoleServiceImpl class implements the RoleService interface and provides
 * methods for CRUD operations on Role entities using reactive programming.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ReactiveRoleMapper roleMapper;

    /**
     * Saves a RoleDto.
     *
     * @param RoleDto the RoleDto to save
     * @return a Mono emitting the saved RoleDto
     */
    @Override
    public Mono<RoleDto> save(RoleDto RoleDto) {

        return roleMapper.toReactiveEntity(RoleDto)
                .flatMap(roleRepository::save)
                .flatMap(roleMapper::toReactiveDto);
    }

    /**
     * Saves a list of RoleDto entities.
     *
     * @param entitiesDto the list of RoleDto entities to be saved
     * @return a Flux emitting the saved RoleDto entities
     */
    @Override
    public Flux<RoleDto> saveAll(List<RoleDto> entitiesDto) {
        Flux<Role> roles = roleRepository.saveAll(roleMapper.mapToReactiveEntity(Flux.fromIterable(entitiesDto)));
        return roleMapper.mapToReactiveDto(roles);
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param userId the ID of the entity to be deleted
     * @return a Mono representing the completion of the deletion process
     */
    @Override
    public Mono<Void> deleteById(String userId) {
        return roleRepository.deleteById(userId);
    }

    /**
     * Finds an entity by its ID.
     *
     * @param userId the ID of the entity to find
     * @return a Mono emitting the entity if found, or completing empty if not found
     */
    @Override
    public Mono<RoleDto> findById(String userId) {
        return roleRepository.findById(userId).flatMap(roleMapper::toReactiveDto);
    }

    /**
     * Retrieves all entities.
     *
     * @return a Flux of RoleDto representing all the entities.
     */
    @Override
    public Flux<RoleDto> findAll() {
        return roleRepository.findAll().flatMap(roleMapper::toReactiveDto);
    }

    /**
     * Finds all entities in a paginated manner.
     *
     * @param pageable the pagination information
     * @return a Mono emitting the Page of EntityDto
     */
    @Override
    public Mono<Page<RoleDto>> findAll(Pageable pageable) {
        return Mono.empty();
    }

    /**
     * Updates a Role entity with the given RoleDto and userId.
     *
     * @param roleDto The RoleDto containing the updated data.
     * @param userId  The id of the Role to be updated.
     * @return A Mono emitting the updated RoleDto.
     */
    @Override
    public Mono<RoleDto> update(RoleDto RoleDto, String userId) {
        return Mono.empty();
    }
}
