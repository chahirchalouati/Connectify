package com.crcl.iam.service.impl;

import com.crcl.iam.domain.User;
import com.crcl.iam.dto.UserDto;
import com.crcl.iam.mappers.RoleMapper;
import com.crcl.iam.mappers.UserMapper;
import com.crcl.iam.mappers.UserMapperImpl;
import com.crcl.iam.repository.UserRepository;
import com.crcl.iam.utils.assertions.UserDtoAssertion;
import com.crcl.iam.utils.builders.UserDtoTestBuilder;
import com.crcl.iam.utils.builders.UserTestBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleMapper roleMapper;
    @Spy
    private UserMapper userMapper = new UserMapperImpl(roleMapper);
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testSaveUser() {

        UserDto userDto = UserDtoTestBuilder.createUserDto()
                .withUsername("testuser")
                .withPassword("password")
                .withAccountNonExpired()
                .withDefaultAvatar()
                .build();

        User user = UserTestBuilder.fromDto(userDto).build();

        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDto savedUser = userService.save(userDto);

        UserDtoAssertion.assertThat(savedUser)
                .hasUsername("testuser")
                .hasPassword("password");
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(any(User.class));
        verify(userMapper).toEntity(userDto);
        verify(userMapper).toDto(user);
    }

}