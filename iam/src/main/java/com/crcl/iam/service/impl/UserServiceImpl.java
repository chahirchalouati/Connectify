package com.crcl.iam.service.impl;

import com.crcl.iam.clients.ProfileClient;
import com.crcl.iam.clients.ServerStorageClient;
import com.crcl.iam.domain.User;
import com.crcl.iam.dto.CreateUserRequest;
import com.crcl.iam.dto.UserDto;
import com.crcl.iam.helpers.AuthenticationHelper;
import com.crcl.iam.mappers.UserMapper;
import com.crcl.iam.repository.UserRepository;
import com.crcl.iam.service.UserService;
import com.crcl.iam.utils.ProfileUtils;
import com.crcl.iam.utils.RoleUtils;
import com.crcl.core.dto.responses.FileUploadResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final ProfileClient profileClient;
    private final AuthenticationHelper authenticationHelper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ServerStorageClient serverStorageClient;

    @Override
    public UserDto save(UserDto userDto) {
        log.debug("Saving user: {}", userDto);
        User user = this.userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(RoleUtils.getDefaultUserRoles());
        User savedUser = userRepository.save(user);
        log.debug("User saved: {}", savedUser);
        return userMapper.toDto(savedUser);
    }

    @Override
    public List<UserDto> saveAll(List<UserDto> entities) {
        log.debug("Saving users: {}", entities);
        List<UserDto> savedUsers = entities.stream()
                .map(this::save).toList();
        log.debug("Users saved: {}", savedUsers);
        return savedUsers;
    }

    @Override
    public void deleteById(String id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setEnabled(false);
            User disabledUser = userRepository.save(user);
            log.debug("User with id {} was disabled: {}", user.getId(), disabledUser);
        });
    }

    @Override
    public UserDto findById(String id) {
        log.debug("Finding user by id: {}", id);
        UserDto foundUser = userRepository.findById(id)
                .map(userMapper::toDto).orElse(null);
        log.debug("User found: {}", foundUser);
        return foundUser;
    }


    @Override
    public List<UserDto> findAll() {
        log.debug("Finding all users");
        List<UserDto> foundUsers = userRepository.getAll(authenticationHelper.getCurrent());
        log.debug("Users found: {}", foundUsers);
        return foundUsers;
    }


    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        log.debug("Finding users by page: {}", pageable);
        Page<UserDto> foundUsers = userRepository.getAll(pageable, authenticationHelper.getCurrent());
        log.debug("Users found: {}", foundUsers);
        return foundUsers;
    }

    @Override
    public UserDto update(UserDto userDto, String id) {
        log.debug("Updating user with id {}: {}", id, userDto);
        User updatedUser = userRepository.findById(id)
                .map(user -> userMapper.toEntity(userDto))
                .map(userRepository::save)
                .orElse(null);
        UserDto updatedUserDto = userMapper.toDto(updatedUser);
        log.debug("User updated: {}", updatedUserDto);
        return updatedUserDto;
    }

    @Override
    public UserDto findByUsername(String username) {
        log.debug("Finding user by username: {}", username);
        UserDto foundUser = userRepository.findByUsernameAllIgnoreCase(username)
                .map(userMapper::toDto).orElse(null);
        log.debug("User found: {}", foundUser);
        return foundUser;
    }

    @Override
    public UserDto save(CreateUserRequest request) {
        log.debug("Creating user: {}", request);
        final User user = userMapper.toEntity(request);
        this.addUserAvatar(request, user);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(RoleUtils.getDefaultUserRoles());
        UserDto savedUser = userMapper.toDto(this.userRepository.save(user));
        log.debug("User created: {}", savedUser);
        return savedUser;
    }

    @Override
    public UserDto getCurrentUser() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsernameAllIgnoreCase((String) jwt.getClaims().get("username"))
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    @Override
    public Set<UserDto> findByUserNames(Set<String> userNames) {
        List<User> users = this.userRepository.findByUsernameIn(userNames);
        this.profileClient.findByUsernames(new ArrayList<>(userNames));
        return new HashSet<>(userMapper.mapToDto(users));
    }

    private void addUserAvatar(CreateUserRequest request, User user) {
        try {
            if (nonNull(request.getAvatarFile())) {
                FileUploadResult fileSaveResponse = this.serverStorageClient.uploadFile(request.getAvatarFile());
                user.setAvatar(fileSaveResponse.getLink());
            }
        } catch (Exception e) {
            log.error("An error occurred while saving avatar for user: {}", user.getUsername(), e);
            user.setAvatar(ProfileUtils.getAvatar(user));
        }
    }
}
