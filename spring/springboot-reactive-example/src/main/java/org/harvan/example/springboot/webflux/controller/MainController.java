/*
 * Copyright 2018-2018 the original author or authors.
 */

package org.harvan.example.springboot.webflux.controller;

import io.reactivex.Flowable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.harvan.example.springboot.webflux.entity.MainEntity;
import org.harvan.example.springboot.webflux.services.MainEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (14 Apr 2018)
 */
@RestController
@RequestMapping("/webflux")
public class MainController {
    private final Log logger = LogFactory.getLog(getClass());
    private MainEntityService mainEntityService;

    Log logger() {
        return logger;
    }

    @Autowired
    public void setMainEntityService(MainEntityService mainEntityService) {
        this.mainEntityService = mainEntityService;
    }

    @GetMapping("/reactive/all")
    public Flux<MainEntity> getAllFlux() {
        return getFrom1().subscribeOn(Schedulers.elastic()).publishOn(Schedulers.elastic());
    }

    @GetMapping("/reactive/all2")
    public Flowable<MainEntity> getAllFlowable() {
        return mainEntityService.findAllFlowable().subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(io.reactivex.schedulers.Schedulers.io());
    }

    private Flux<MainEntity> getFrom1() {
        return mainEntityService.findAllFlux();
    }

    private Flux<MainEntity> getFrom2() {
        return mainEntityService.findAllFlux().subscribeOn(Schedulers.parallel()).publishOn(Schedulers.parallel());
    }

    private Flux<MainEntity> getFrom3() {
        return mainEntityService.findAllFlux().doOnRequest(value -> {
        }).subscribeOn(Schedulers.parallel()).publishOn(Schedulers.parallel());
    }

    private Flux<MainEntity> getFrom4() {
        return Flux.<MainEntity>fromIterable(() -> mainEntityService.findAllFlux().collectList().block().iterator());
    }

    @GetMapping("/reactive/{id}")
    public Mono<MainEntity> getById(@PathVariable Integer id) {
        return mainEntityService.findById(id);
    }

    @GetMapping("/blocking/all")
    public List<MainEntity> allBlocking() {
        if (logger().isDebugEnabled()) {
            logger().debug("Logging blocking...");
        }

        return mainEntityService.findAllBlocking();
    }
}