package com.crcl.iam.repositories.impl;

import com.crcl.iam.domain.User;
import com.crcl.iam.repositories.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final ReactiveCassandraTemplate reactiveCassandraTemplate;

    @Override
    public Mono<Page<User>> findAll(Pageable pageable) {
        Query query = Query.empty().pageRequest(pageable);

        return reactiveCassandraTemplate.select(query, User.class)
                .collectList()
                .map(users -> new PageImpl<>(users, pageable, users.size()));
    }
}
