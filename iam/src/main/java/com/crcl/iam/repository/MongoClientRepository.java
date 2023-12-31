package com.crcl.iam.repository;

import com.crcl.iam.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
}
