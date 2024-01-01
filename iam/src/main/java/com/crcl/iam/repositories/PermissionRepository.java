package com.crcl.iam.repositories;

import com.crcl.iam.domain.Permission;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * The PermissionRepository interface is responsible for interacting with the database to perform CRUD operations for Permission entities.
 * It extends the ReactiveCassandraRepository interface, which provides default implementations for common database operations.
 * <p>
 * This interface provides an additional method to find a Permission by its name.
 * <p>
 * The Permission class represents a permission entity in the database.
 * It is annotated with @Document to define the table name and @Data to generate necessary getters, setters, equals, hashcode, and toString methods.
 * <p>
 * The Permission class has the following fields:
 * - id: a UUID representing the unique identifier for the permission
 * - timeUUID: a UUID generated based on the current timestamp for ordering purposes
 * - enabled: a boolean indicating whether the permission is enabled or disabled
 * - name: a String representing the name of the permission
 * <p>
 * The Permission class also has a constructor that accepts the name parameter.
 */
public interface PermissionRepository extends ReactiveMongoRepository<Permission, String> {

    /**
     * Finds a Permission entity from the database by its name.
     *
     * @param name the name of the permission to find
     * @return a Mono that emits the found Permission entity if it exists, or completes empty if not found
     */
    Mono<Permission> findByName(String name);
}
