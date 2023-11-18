package com.crcl.iam.controllers;

import com.crcl.iam.dto.PermissionDto;
import com.crcl.iam.servcies.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    public Mono<PermissionDto> save(@RequestBody @Valid PermissionDto PermissionDto) {
        return permissionService.save(PermissionDto);
    }

    @PostMapping("/saveAll")
    public Flux<PermissionDto> saveAll(@RequestBody @Valid List<PermissionDto> userDtos) {
        return permissionService.saveAll(userDtos);
    }

    @DeleteMapping("/{permissionId}")
    public Mono<Void> deleteById(@PathVariable String permissionId) {
        return permissionService.deleteById(permissionId);
    }

    @GetMapping("/{permissionId}")
    public Mono<PermissionDto> findById(@PathVariable String permissionId) {
        return permissionService.findById(permissionId);
    }

    @GetMapping
    public Flux<PermissionDto> findAll() {
        return permissionService.findAll();
    }

    @PutMapping("/{permissionId}")
    public Mono<PermissionDto> update(@RequestBody PermissionDto PermissionDto, @PathVariable String permissionId) {
        return permissionService.update(PermissionDto, permissionId);
    }
}
