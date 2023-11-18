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
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ReactiveUserMapper userMapper;

    @Override
    public Mono<UserDto> save(UserDto userDto) {

        return userMapper.toReactiveEntity(userDto)
                .flatMap(userRepository::save)
                .flatMap(userMapper::toReactiveDto);
    }

    @Override
    public Flux<UserDto> saveAll(List<UserDto> entitiesDto) {
        Flux<User> users = userRepository.saveAll(userMapper.mapToReactiveEntity(Flux.fromIterable(entitiesDto)));
        return userMapper.mapToReactiveDto(users);
    }

    @Override
    public Mono<Void> deleteById(String userId) {
        return userRepository.deleteById(userId);
    }

    @Override
    public Mono<UserDto> findById(String userId) {
        return userRepository.findById(userId).flatMap(userMapper::toReactiveDto);
    }

    @Override
    public Flux<UserDto> findAll() {
        return userRepository.findAll().flatMap(userMapper::toReactiveDto);
    }

    @Override
    public Mono<Page<UserDto>> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(users -> new PageImpl<>(users.map(userMapper::toDto).getContent(), pageable, users.getSize()));
    }

    @Override
    public Mono<UserDto> update(UserDto userDto, String userId) {
        return Mono.empty();
    }
}
