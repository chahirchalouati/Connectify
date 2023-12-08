package com.crcl.core.service.generic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * This interface represents a reactive generic mapper that provides methods to convert between entity objects and DTOs (Data Transfer Objects) in a reactive way using Project React
 *or.
 *
 * @param <ENTITY> the type of the entity objects
 * @param <DTO> the type of the DTOs
 */
public interface ReactiveGenericMapper<ENTITY, DTO> extends GenericMapper<ENTITY, DTO> {

    /**
     * Converts a DTO (Data Transfer Object) to a reactive entity object.
     *
     * @param dto the DTO to be converted
     * @return a Mono representing the reactive entity object
     */
    default Mono<ENTITY> toReactiveEntity(DTO dto){
        return Mono.just(toEntity(dto));
    };

    /**
     * Converts an entity object to a reactive DTO object.
     *
     * @param entity the entity object to convert
     * @return a Mono emitting the resulting DTO object
     */
    default Mono<DTO> toReactiveDto(ENTITY entity){
        return Mono.just(toDto(entity));
    };

    /**
     * Maps a Flux of DTO objects to a Flux of reactive entity objects using the {@code toReactiveEntity} method.
     *
     * @param dtoList the Flux of DTO objects
     * @return a Flux of reactive entity objects
     */
    default Flux<ENTITY> mapToReactiveEntity(Flux<DTO> dtoList){
        return dtoList.flatMap(this::toReactiveEntity);
    }

    /**
     * Maps a flux of entity objects to a flux of reactive DTOs.
     *
     * @param entityList the flux of entity objects to be mapped
     * @return the flux of reactive DTOs
     */
    default  Flux<DTO> mapToReactiveDto(Flux<ENTITY> entityList){
        return entityList.flatMap(this::toReactiveDto);
    }

}

