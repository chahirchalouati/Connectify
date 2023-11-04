package com.crcl.iam.repository;

import com.crcl.iam.domain.Key;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Set;

public interface RSAKeyRepository extends MongoRepository<Key, String> {
    @Query("{'isValid': true}")
    Set<Key> findByValid();
}
