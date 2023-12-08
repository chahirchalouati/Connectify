package com.crcl.core.service.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * The ReactiveGenericService interface provides methods for CRUD operations on entities using reactive programming.
 *
 * @param <EntityDto> the type of the entity DTO
 * @param <EntityId>  the type of the entity ID
 */
public interface ReactiveGenericService<EntityDto, EntityId> {
    /**
     * Saves an entity.
     *
     * @param entityDto the entity DTO to save
     * @return a Mono emitting the saved entity DTO
     */
    Mono<EntityDto> save(EntityDto entityDto);

    /**
     * Saves a list of entity DTOs.
     *
     * @param entitiesDto the list of entity DTOs to be saved
     * @return a Flux emitting the saved entity DTOs
     */
    Flux<EntityDto> saveAll(List<EntityDto> entitiesDto);

    /**
     * Deletes an entity by its ID.
     *
     * @param entityId the ID of the entity to be deleted
     * @return a Mono representing the completion of the deletion process
     */
    Mono<Void> deleteById(EntityId entityId);

    /**
     * Finds an entity by its ID.
     *
     * @param entityId the ID of the entity to find
     * @return a Mono emitting the entity if found, or completing empty if not found
     */
    Mono<EntityDto> findById(EntityId entityId);

    /**
     * Retrieves all entities.
     *
     * @return a Flux of EntityDto representing all the entities.
     */
    Flux<EntityDto> findAll();

    /**
     * Finds all entities in a paginated manner.
     *
     * @param pageable the pagination information
     * @return a Mono emitting the Page of EntityDto
     */
    Mono<Page<EntityDto>> findAll(Pageable pageable);

    /**
     * Updates an entity with the given entityDto and entityId.
     *
     * @param entityDto The entityDto containing the updated data.
     * @param entityId The id of the entity to be updated.
     * @return A Mono emitting the updated entityDto.
     */
    Mono<EntityDto> update(EntityDto entityDto, EntityId entityId);
}
