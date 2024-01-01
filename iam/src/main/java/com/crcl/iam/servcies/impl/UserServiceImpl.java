package com.crcl.iam.servcies.impl;

import com.crcl.iam.domain.User;
import com.crcl.iam.dto.UserDto;
import com.crcl.iam.mappers.ReactiveUserMapper;
import com.crcl.iam.repositories.UserRepository;
import com.crcl.iam.servcies.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The implementation class for the UserService interface.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReactiveUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Saves a UserDto object.
     *
     * @param userDto the UserDto object to be saved
     * @return a Mono emitting the saved UserDto object
     */
    @Override
    public Mono<UserDto> save(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userMapper.toReactiveEntity(userDto)
                .flatMap(userRepository::save)
                .flatMap(userMapper::toReactiveDto);
    }

    /**
     * Saves a list of UserDto objects and returns a Flux of UserDto objects.
     *
     * @param entitiesDto the list of UserDto objects to be saved
     * @return a Flux of UserDto objects
     */
    @Override
    public Flux<UserDto> saveAll(List<UserDto> entitiesDto) {
        Flux<User> users = userRepository.saveAll(userMapper.mapToReactiveEntity(Flux.fromIterable(entitiesDto)));
        return userMapper.mapToReactiveDto(users);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to be deleted
     * @return a Mono representing the completion of the delete operation
     */
    @Override
    public Mono<Void> deleteById(String userId) {
        return userRepository.deleteById(userId);
    }

    /**
     * Finds a user by their user ID.
     *
     * @param userId the ID of the user to find
     * @return a Mono emitting the found UserDto object, or empty if not found
     */
    @Override
    public Mono<UserDto> findById(String userId) {
        return userRepository.findById(userId).flatMap(userMapper::toReactiveDto);
    }

    /**
     * Retrieves all users.
     *
     * @return a Flux emitting UserDto objects
     */
    @Override
    public Flux<UserDto> findAll() {
        return userRepository.findAll().flatMap(userMapper::toReactiveDto);
    }

    /**
     * Finds all users with pagination.
     *
     * @param pageable the pageable object specifying the page size and sort order
     * @return a Mono emitting a Page of UserDto objects
     */
    @Override
    public Mono<Page<UserDto>> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(users -> new PageImpl<>(users.map(userMapper::toDto).getContent(), pageable, users.getSize()));
    }

    /**
     * Updates a user with the provided userDto and userId.
     *
     * @param userDto The DTO representing the updated user data.
     * @param userId  The ID of the user to update.
     * @return A Mono emitting the updated UserDto.
     */
    @Override
    public Mono<UserDto> update(UserDto userDto, String userId) {
        return Mono.empty();
    }
}
