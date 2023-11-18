package com.crcl.core.service.generic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveGenericMapper<ENTITY, DTO> extends GenericMapper<ENTITY, DTO> {

    default Mono<ENTITY> toReactiveEntity(DTO dto){
        return Mono.just(toEntity(dto));
    };

    default Mono<DTO> toReactiveDto(ENTITY entity){
        return Mono.just(toDto(entity));
    };

    default Flux<ENTITY> mapToReactiveEntity(Flux<DTO> dtoList){
        return dtoList.flatMap(this::toReactiveEntity);
    }

    default  Flux<DTO> mapToReactiveDto(Flux<ENTITY> entityList){
        return entityList.flatMap(this::toReactiveDto);
    }

}

