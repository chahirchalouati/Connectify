package com.crcl.iam.repositories;

import com.crcl.iam.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;


public interface CustomUserRepository {
    /**
     * Retrieves all users with pagination.
     *
     * @param pageable the pageable object specifying the page size and sort order
     * @return a Mono emitting a Page of User objects
     */
    Mono<Page<User>> findAll(Pageable pageable);
}
