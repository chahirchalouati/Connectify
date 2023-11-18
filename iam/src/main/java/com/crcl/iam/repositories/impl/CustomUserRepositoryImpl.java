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

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Page<User>> findAll(Pageable pageable) {
        Query query = new Query().with(pageable);

        return mongoTemplate.find(query, User.class)
                .collectList()
                .map(notificationTypes -> new PageImpl<>(notificationTypes, pageable, notificationTypes.size()));
    }

}
