package org.harvan.example.springboot.webflux.repository;

import io.reactivex.Flowable;
import org.harvan.example.springboot.webflux.entity.MainEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MainEntityRepository {
    Mono<MainEntity> findById(Integer id);

    Flux<MainEntity> findAllFlux();

    Flowable<MainEntity> findAllFlowable();

    List<MainEntity> findAllBlocking();
}