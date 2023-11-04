package com.crcl.iam.repository;


import com.crcl.iam.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(String name);

}
