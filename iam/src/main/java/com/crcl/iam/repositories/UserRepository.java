package com.crcl.iam.repositories;

import com.crcl.iam.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.observability.SignalListener;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String>, CustomUserRepository {

    Mono<User> findByUsername(String username);

    Mono<User>  findByEmail(String email);
}
