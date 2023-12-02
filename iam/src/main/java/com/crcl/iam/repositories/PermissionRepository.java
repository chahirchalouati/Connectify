package com.crcl.iam.repositories;

import com.crcl.iam.domain.Permission;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;

public interface PermissionRepository extends ReactiveCassandraRepository<Permission, String> {

    Mono<Permission> findByName(String name);
}
