package com.crcl.iam.controllers;

import com.crcl.iam.dto.UserDto;
import com.crcl.iam.servcies.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

/**
 * User Controller to manage CRUD operations on Users.
 */
@OpenAPIDefinition(info = @Info(title = "UserController API", version = "1.0", description = "API for User Controller"))
@Tag(name = "User Controller", description = "The user controller API")
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    @Schema(description = "Service for user operations")
    private final UserService userService;

    @Operation(summary = "Add a new user to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @PostMapping
    public Mono<UserDto> save(@RequestBody @Valid @Schema(description = "User to save") UserDto userDto) {
        return userService.save(userDto);
    }

    @Operation(summary = "Add a list of new users to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @PostMapping("/saveAll")
    public Flux<UserDto> saveAll(@RequestBody @Valid @Schema(description = "Users to save") List<UserDto> userDtos) {
        return userService.saveAll(userDtos);
    }

    @Operation(summary = "Deletes a user from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @DeleteMapping("/{userId}")
    public Mono<Void> deleteById(@PathVariable String userId) {
        return userService.deleteById(userId);
    }

    @Operation(summary = "Retrieves a user from the system by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @GetMapping("/{userId}")
    public Mono<UserDto> findById(@PathVariable String userId) {
        return userService.findById(userId);
    }

    @Operation(summary = "Retrieve all users as page from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @GetMapping
    public Mono<Page<UserDto>> findAll(Pageable pageable) {
        return userService.findAll(pageable).delayElement(Duration.ofSeconds(1));
    }

    @Operation(summary = "Updates a user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Server error")})
    @PutMapping("/{userId}")
    public Mono<UserDto> update(@RequestBody UserDto userDto, @PathVariable String userId) {
        return userService.update(userDto, userId);
    }
}