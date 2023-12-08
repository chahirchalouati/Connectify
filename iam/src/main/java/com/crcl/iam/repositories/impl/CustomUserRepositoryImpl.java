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

/**
 * Implementation of the {@link CustomUserRepository} interface. It provides methods for querying and manipulating user data in the database.
 */
@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final ReactiveCassandraTemplate reactiveCassandraTemplate;

    /**
     * Finds all users in the database and returns them in a paginated format.
     *
     * @param pageable The pagination information.
     * @return A Mono emitting the Page object containing the list of users and pagination details.
     */
    @Override
    public Mono<Page<User>> findAll(Pageable pageable) {
        Query query = Query.empty().pageRequest(pageable);

        return reactiveCassandraTemplate.select(query, User.class)
                .collectList()
                .map(users -> new PageImpl<>(users, pageable, users.size()));
    }
}
