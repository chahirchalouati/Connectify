package com.crcl.iam.controllers;

import com.crcl.iam.dto.RoleDto;
import com.crcl.iam.servcies.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public Mono<RoleDto> save(@RequestBody @Valid RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @PostMapping("/saveAll")
    public Flux<RoleDto> saveAll(@RequestBody @Valid List<RoleDto> roleDtos) {
        return roleService.saveAll(roleDtos);
    }

    @DeleteMapping("/{roleId}")
    public Mono<Void> deleteById(@PathVariable String roleId) {
        return roleService.deleteById(roleId);
    }

    @GetMapping("/{roleId}")
    public Mono<RoleDto> findById(@PathVariable String roleId) {
        return roleService.findById(roleId);
    }

    @GetMapping
    public Flux<RoleDto> findAll() {
        return roleService.findAll();
    }

    @PutMapping("/{roleId}")
    public Mono<RoleDto> update(@RequestBody RoleDto RoleDto, @PathVariable String roleId) {
        return roleService.update(RoleDto, roleId);
    }
}
