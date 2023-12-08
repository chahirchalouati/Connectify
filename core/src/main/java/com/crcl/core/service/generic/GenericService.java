package com.crcl.core.service.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * This interface represents a generic service for managing entities.
 *
 * @param <EntityDto> The type of entity DTO.
 * @param <EntityId>  The type of entity ID.
 */
public interface GenericService<EntityDto, EntityId> {
    /**
     * Saves an entity.
     *
     * @param entityDto the entity to be saved
     * @return the saved entity
     */
    EntityDto save(EntityDto entityDto);

    /**
     * Saves all the entities provided in the list.
     *
     * @param entitiesDto the list of entities to be saved
     * @return a list of saved entities
     */
    List<EntityDto> saveAll(List<EntityDto> entitiesDto);

    /**
     * Deletes an entity with the given ID.
     *
     * @param entityId the ID of the entity to be deleted
     */
    void deleteById(EntityId entityId);

    /**
     * Finds an entity by its ID.
     *
     * @param entityId the ID of the entity to find
     * @return the found entity as an EntityDto object
     */
    EntityDto findById(EntityId entityId);

    /**
     * Retrieves all entities.
     *
     * @return a list of EntityDto objects representing all entities
     */
    List<EntityDto> findAll();

    /**
     * Find all entities with pagination support.
     *
     * @param pageable the pagination information
     * @return a page of entity DTOs
     */
    Page<EntityDto> findAll(Pageable pageable);

    /**
     * Updates an entity with the given entityDto using the provided entityId.
     *
     * @param entityDto The entityDto representing the updated entity.
     * @param entityId  The entityId of the entity to be updated.
     * @return The updated entityDto.
     */
    EntityDto update(EntityDto entityDto, EntityId entityId);
}
