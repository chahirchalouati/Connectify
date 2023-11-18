package com.crcl.iam.repositories;

import com.crcl.iam.domain.Role;
import com.crcl.iam.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface RoleRepository extends ReactiveMongoRepository<Role, String> {

    Mono<Role> findByName(String name);
}
