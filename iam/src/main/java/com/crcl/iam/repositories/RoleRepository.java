package com.crcl.iam.repositories;

import com.crcl.iam.domain.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * The RoleRepository interface provides methods for CRUD operations on Role entities using reactive programming.
 */
public interface RoleRepository extends ReactiveMongoRepository<Role, String> {

    /**
     * Retrieves a {@link Mono} representing the role with the given name.
     *
     * @param name the name of the role to find
     * @return a {@link Mono} emitting the role with the given name, or empty if not found
     */
    Mono<Role> findByName(String name);
}
