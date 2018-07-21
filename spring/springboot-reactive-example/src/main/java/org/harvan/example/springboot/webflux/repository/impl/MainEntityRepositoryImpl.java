package org.harvan.example.springboot.webflux.repository.impl;

import io.reactivex.Flowable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.harvan.example.springboot.webflux.entity.MainEntity;
import org.harvan.example.springboot.webflux.entity.MainEntityBuilder;
import org.harvan.example.springboot.webflux.repository.MainEntityRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MainEntityRepositoryImpl implements MainEntityRepository, InitializingBean {
    private static final int SLEEP = 3000;
    private List<MainEntity> mainEntities;
    private final Log logger = LogFactory.getLog(getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        mainEntities = new ArrayList<>();

        mainEntities.add(buildMainEntity(101));
        mainEntities.add(buildMainEntity(102));
        mainEntities.add(buildMainEntity(103));
        mainEntities.add(buildMainEntity(104));
    }

    private MainEntity buildMainEntity(int id) {
        return new MainEntityBuilder().withId(id).withName("name").withDescription("description").build();
    }

    @Override
    public Mono<MainEntity> findById(Integer id) {
        return Mono.just(buildMainEntity(100));
    }

    @Override
    public Flux<MainEntity> findAllFlux() {
        printThreadName();
        return Flux.fromIterable(mainEntities).doOnRequest(value -> sleep());
    }

    @Override
    public Flowable<MainEntity> findAllFlowable() {
        printThreadName();
        return Flowable.fromIterable(mainEntities).doOnRequest(t -> sleep());
    }

    private void sleep() {
        try {
            printThreadName();
            Thread.sleep(SLEEP);
        } catch (InterruptedException e) {
            logger.error("Error.", e);

            Thread.currentThread().interrupt();
        }
    }

    private void printThreadName() {
        logger.debug("Thread class: " + Thread.currentThread().getClass());
        logger.debug("Thread name: " + Thread.currentThread().getName());
    }

    @Override
    public List<MainEntity> findAllBlocking() {
        return new ArrayList<>(mainEntities);
    }
}