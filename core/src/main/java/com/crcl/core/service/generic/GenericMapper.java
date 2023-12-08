package com.crcl.core.service.generic;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * This interface represents a generic mapper that provides methods to convert between entity objects and DTOs (Data Transfer Objects).
 *
 * @param <E> the type of the entity objects
 * @param <D> the type of the DTOs
 */
public interface GenericMapper<E, D> {
    /**
     * Converts a DTO (Data Transfer Object) to an entity object.
     *
     * @param dto the DTO to be converted
     * @return the entity object
     */
    E toEntity(D dto);

    /**
     * Converts an entity object to a DTO object.
     *
     * @param entity the entity object to convert
     * @return the resulting DTO object
     */
    D toDto(E entity);

    /**
     * Maps a list of DTOs to a list of entity objects using the mapping provided by the implementing class.
     *
     * @param dtoList the list of DTOs to be mapped
     * @return a list of entity objects mapped from the DTOs
     */
    List<E> mapToEntity(List<D> dtoList);

    /**
     * Maps a list of entity objects to a list of DTOs.
     *
     * @param entityList the list of entity objects to be mapped to DTOs
     * @return the list of DTOs that correspond to the entity objects
     */
    List<D> mapToDto(List<E> entityList);

    /**
     * Returns an optional entity object converted from the given DTO object.
     *
     * @param dto the DTO object to convert
     * @return an optional entity object, or an empty optional if the provided DTO is null
     */
    default Optional<E> toOptionalEntity(D dto) {
        return Optional.ofNullable(this.toEntity(dto));
    }

    /**
     * Returns an Optional DTO object by converting the given entity object using the {@link #toDto(E entity)} method.
     * If the result of the conversion is null, an empty Optional is returned.
     *
     * @param entity the entity object to be converted
     * @return an Optional containing the DTO object, or an empty Optional if the conversion result is null
     */
    default Optional<D> toOptionalDto(E entity) {
        return Optional.ofNullable(this.toDto(entity));
    }

    /**
     * Returns a Function that converts a DTO object to an entity object using the implementation of the toEntity() method.
     *
     * @return a Function that converts a DTO object to an entity object
     */
    default Function<D, E> fnToEntity() {
        return this::toEntity;
    }

    /**
     * Returns a {@code Function} that takes an argument of type {@code E} and returns a value of type {@code D}.
     * This {@code Function} implementation is a reference to the {@link #toDto(E entity)} method defined in the
     * {@code GenericMapper} interface.
     *
     * @return the {@code Function} reference to the {@code toDto} method
     * @see GenericMapper#toDto(Object)
     */
    default Function<E, D> fnToDto() {
        return this::toDto;
    }
}
