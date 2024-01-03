package com.crcl.iam.repositories.impl;

import com.crcl.iam.domain.User;
import com.crcl.iam.repositories.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * Implementation of the {@link CustomUserRepository} interface. It provides methods for querying and manipulating user data in the database.
 */
@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Page<User>> findAll(final Pageable pageable) {
        final Query query = new Query().with(pageable);
        return reactiveMongoTemplate.find(query, User.class)
                .collectList()
                .flatMap(users -> reactiveMongoTemplate.count(new Query(), User.class)
                        .map(total -> new PageImpl<>(users, pageable, total))
                );
    }
}
