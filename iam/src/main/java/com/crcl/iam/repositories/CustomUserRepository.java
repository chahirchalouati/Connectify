package com.crcl.iam.repositories;

import com.crcl.iam.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface CustomUserRepository {
    Mono<Page<User>> findAll(Pageable pageable);
}
