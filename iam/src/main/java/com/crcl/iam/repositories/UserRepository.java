package com.crcl.iam.repositories;

import com.crcl.iam.domain.User;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCassandraRepository<User, String>, CustomUserRepository {

    Mono<User> findByUserName(String userName);

    Mono<User> findByEmail(String email);
}
