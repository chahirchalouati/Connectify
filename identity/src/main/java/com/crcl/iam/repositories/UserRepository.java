package com.crcl.iam.repositories;

import com.crcl.iam.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * The UserRepository interface is responsible for interacting with the database to perform CRUD operations on User objects.
 * It extends the ReactiveCassandraRepository interface and the CustomUserRepository interface.
 */
public interface UserRepository extends ReactiveMongoRepository<User, String>, CustomUserRepository {

    /**
     * Retrieves a User object from the database based on the provided username.
     *
     * @param userName the username of the user to retrieve
     * @return a Mono that emits the User object for the specified username
     */
    Mono<User> findByUserName(String userName);

    /**
     * Finds a user by email.
     *
     * @param email the email address of the user to find
     * @return a Mono emitting the User object if found, otherwise empty
     */
    Mono<User> findByEmail(String email);
}
