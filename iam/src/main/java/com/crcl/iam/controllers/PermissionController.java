package com.crcl.iam.controllers;

import com.crcl.iam.dto.PermissionDto;
import com.crcl.iam.servcies.PermissionService;
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

/**
 * Permission Controller to manage CRUD operations on Permissions.
 */
@OpenAPIDefinition(info = @Info(title = "PermissionController API", version = "1.0", description = "API for Permission Controller"))
@Tag(name = "Permission Controller", description = "The Permission Controller API")
@RestController
@RequestMapping("permissions")
@RequiredArgsConstructor
public class PermissionController {

    @Schema(description = "Service for permission operations")
    private final PermissionService permissionService;

    @Operation(summary = "Add a new permission to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping
    public Mono<PermissionDto> save(@RequestBody @Valid @Schema(description = "Permission to save") PermissionDto PermissionDto) {
        return permissionService.save(PermissionDto);
    }

    @Operation(summary = "Add a list of new permissions to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permissions created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping("/saveAll")
    public Flux<PermissionDto> saveAll(@RequestBody @Valid @Schema(description = "Permissions to save") List<PermissionDto> userDtos) {
        return permissionService.saveAll(userDtos);
    }

    @Operation(summary = "Deletes a permission from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission deleted"),
            @ApiResponse(responseCode = "404", description = "Permission not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @DeleteMapping("/{permissionId}")
    public Mono<Void> deleteById(@PathVariable String permissionId) {
        return permissionService.deleteById(permissionId);
    }

    @Operation(summary = "Retrieves a permission from the system by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission found"),
            @ApiResponse(responseCode = "404", description = "Permission not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping("/{permissionId}")
    public Mono<PermissionDto> findById(@PathVariable String permissionId) {
        return permissionService.findById(permissionId);
    }

    @Operation(summary = "Retrieve all permissions from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permissions found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping
    public Flux<PermissionDto> findAll() {
        return permissionService.findAll();
    }

    @Operation(summary = "Updates a permission in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Permission not found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PutMapping("/{permissionId}")
    public Mono<PermissionDto> update(@RequestBody @Schema(description = "Permission data to update") PermissionDto PermissionDto, @PathVariable String permissionId) {
        return permissionService.update(PermissionDto, permissionId);
    }
}