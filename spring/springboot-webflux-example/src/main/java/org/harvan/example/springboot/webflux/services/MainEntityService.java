package org.harvan.example.springboot.webflux.services;

import org.harvan.example.springboot.webflux.entity.MainEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MainEntityService {
    Mono<MainEntity> findById(Integer id);

    Flux<MainEntity> findAll();
}