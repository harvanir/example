package org.harvan.example.springboot.webflux.repository.impl;

import org.harvan.example.springboot.webflux.entity.MainEntity;
import org.harvan.example.springboot.webflux.entity.MainEntityBuilder;
import org.harvan.example.springboot.webflux.repository.MainEntityRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MainEntityRepositoryImpl implements MainEntityRepository {
    @Override
    public Mono<MainEntity> findById(Integer id) {
        return Mono.just(buildMainEntity(100));
    }

    private MainEntity buildMainEntity(int id) {
        return new MainEntityBuilder().withId(id).withName("name").withDescription("description").build();
    }

    @Override
    public Flux<MainEntity> findAll() {
        MainEntity[] list = new MainEntity[4];

        list[0] = buildMainEntity(101);
        list[1] = buildMainEntity(102);
        list[2] = buildMainEntity(103);
        list[3] = buildMainEntity(104);

        return Flux.just(list);
    }
}