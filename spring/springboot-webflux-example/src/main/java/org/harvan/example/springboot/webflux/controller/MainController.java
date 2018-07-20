/*
 * Copyright 2018-2018 the original author or authors.
 */

package org.harvan.example.springboot.webflux.controller;

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

    @GetMapping("/")
    public Flux<MainEntity> all() {
        if (logger().isDebugEnabled()) {
            logger().debug("Logging...");
        }

        return mainEntityService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<MainEntity> getById(@PathVariable Integer id) {
        return mainEntityService.findById(id);
    }
}