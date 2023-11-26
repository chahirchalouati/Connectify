package com.crcl.iam.controllers;

import com.crcl.iam.dto.UserDto;
import com.crcl.iam.servcies.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Mono<UserDto> save(@RequestBody @Valid UserDto userDto) {
        return userService.save(userDto);
    }

    @PostMapping("/saveAll")
    public Flux<UserDto> saveAll(@RequestBody @Valid List<UserDto> userDtos) {
        return userService.saveAll(userDtos);
    }

    @DeleteMapping("/{userId}")
    public Mono<Void> deleteById(@PathVariable String userId) {
        return userService.deleteById(userId);
    }

    @GetMapping("/{userId}")
    public Mono<UserDto> findById(@PathVariable String userId) {
        return userService.findById(userId);
    }

    @GetMapping
    public Mono<Page<UserDto>> findAll(Pageable pageable) {
        return userService.findAll(pageable).delayElement(Duration.ofSeconds(1));
    }

    @PutMapping("/{userId}")
    public Mono<UserDto> update(@RequestBody UserDto userDto, @PathVariable String userId) {
        return userService.update(userDto, userId);
    }
}
