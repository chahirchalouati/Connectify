package com.crcl.iam.repositories;

import com.crcl.iam.domain.Role;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Mono;

public interface RoleRepository extends ReactiveCassandraRepository<Role, String> {

    Mono<Role> findByName(String name);
}
