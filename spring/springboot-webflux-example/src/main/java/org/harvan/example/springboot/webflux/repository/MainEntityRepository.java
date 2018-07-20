package org.harvan.example.springboot.webflux.repository;

import org.harvan.example.springboot.webflux.entity.MainEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MainEntityRepository {
    Mono<MainEntity> findById(Integer id);

    Flux<MainEntity> findAll();
}