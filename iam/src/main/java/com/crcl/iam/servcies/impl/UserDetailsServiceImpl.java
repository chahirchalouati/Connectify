package com.crcl.iam.servcies.impl;

import com.crcl.iam.domain.User;
import com.crcl.iam.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userRepository.findByUserName(username)
                .map(this::toUserDetails);
    }

    private UserDetails toUserDetails(User user) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return user.getAuthorities();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUserName();
            }

            @Override
            public boolean isAccountNonExpired() {
                return user.isAccountNonExpired();
            }

            @Override
            public boolean isAccountNonLocked() {
                return user.isAccountNonLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return user.isCredentialsNonExpired();
            }

            @Override
            public boolean isEnabled() {
                return user.isEnabled();
            }
        };
    }
}
