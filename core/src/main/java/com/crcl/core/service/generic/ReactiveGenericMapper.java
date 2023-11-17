package com.crcl.core.service.generic;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public interface ReactiveGenericMapper<E, D> {

        Mono<E> toEntity(D dto);

        Mono<D> toDto(E entity);

        Flux<E> mapToEntity(Flux<D> dtoList);

        Flux<D> mapToDto(Flux<E> entityList);

    }

