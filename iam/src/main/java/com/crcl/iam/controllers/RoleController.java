package com.crcl.iam.controllers;

import com.crcl.iam.dto.RoleDto;
import com.crcl.iam.servcies.RoleService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "RoleController API", version = "1.0", description = "API for Role Controller"))
@Tag(name = "Role Controller", description = "The role controller API")
@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleController {

    @Schema(description = "Service for role operations")
    private final RoleService roleService;

    @Operation(summary = "Add a new role to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @PostMapping
    public Mono<RoleDto> save(@RequestBody @Valid @Schema(description = "Role to save") RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @Operation(summary = "Add a list of new roles to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @PostMapping("/saveAll")
    public Flux<RoleDto> saveAll(@RequestBody @Valid @Schema(description = "Roles to save") List<RoleDto> roleDtos) {
        return roleService.saveAll(roleDtos);
    }

    @Operation(summary = "Deletes a role from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted"),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @DeleteMapping("/{roleId}")
    public Mono<Void> deleteById(@PathVariable String roleId) {
        return roleService.deleteById(roleId);
    }

    @Operation(summary = "Retrieves a role from the system by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found"),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @GetMapping("/{roleId}")
    public Mono<RoleDto> findById(@PathVariable String roleId) {
        return roleService.findById(roleId);
    }

    @Operation(summary = "Retrieve all roles from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles found"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @GetMapping
    public Flux<RoleDto> findAll() {
        return roleService.findAll();
    }

    @Operation(summary = "Updates a role in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @PutMapping("/{roleId}")
    public Mono<RoleDto> update(@RequestBody @Schema(description = "Details of the role to be updated") RoleDto RoleDto, @PathVariable String roleId) {
        return roleService.update(RoleDto, roleId);
    }
}