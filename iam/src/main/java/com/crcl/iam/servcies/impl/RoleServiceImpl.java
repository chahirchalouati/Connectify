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

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ReactiveRoleMapper roleMapper;

    @Override
    public Mono<RoleDto> save(RoleDto RoleDto) {

        return roleMapper.toReactiveEntity(RoleDto)
                .flatMap(roleRepository::save)
                .flatMap(roleMapper::toReactiveDto);
    }

    @Override
    public Flux<RoleDto> saveAll(List<RoleDto> entitiesDto) {
        Flux<Role> roles = roleRepository.saveAll(roleMapper.mapToReactiveEntity(Flux.fromIterable(entitiesDto)));
        return roleMapper.mapToReactiveDto(roles);
    }

    @Override
    public Mono<Void> deleteById(String userId) {
        return roleRepository.deleteById(userId);
    }

    @Override
    public Mono<RoleDto> findById(String userId) {
        return roleRepository.findById(userId).flatMap(roleMapper::toReactiveDto);
    }

    @Override
    public Flux<RoleDto> findAll() {
        return roleRepository.findAll().flatMap(roleMapper::toReactiveDto);
    }

    @Override
    public Mono<Page<RoleDto>> findAll(Pageable pageable) {
        return Mono.empty();
    }

    @Override
    public Mono<RoleDto> update(RoleDto RoleDto, String userId) {
        return Mono.empty();
    }
}
