package com.crcl.iam.repositories;

import com.crcl.iam.domain.Permission;
import com.crcl.iam.domain.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PermissionRepository extends ReactiveMongoRepository<Permission, String> {

    Mono<Permission> findByName(String name);
}
