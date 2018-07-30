package org.harvan.example.fullstack.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.harvan.example.fullstack.dto.PersonDtoRequest;
import org.harvan.example.fullstack.dto.PersonDtoResponse;
import org.harvan.example.fullstack.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    private Logger logger = LogManager.getLogger(getClass());
    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/ping")
    public Mono<String> ping() {
        return Mono.just("pinged...");
    }

    @GetMapping()
    public Flux<PersonDtoResponse> findAll() {
        return personService.findAll().subscribeOn(Schedulers.elastic()).publishOn(Schedulers.elastic());
    }


    @PostMapping("/")
    public Mono<PersonDtoResponse> create(@RequestBody PersonDtoRequest personDtoRequest) {
        return personService.save(personDtoRequest).map(emitter -> {
            if (logger.isDebugEnabled()) {
                logger.debug("personDtoRequest: {}", personDtoRequest);
                logger.debug("returned save: {}", emitter);
            }

            return emitter;
        }).subscribeOn(Schedulers.elastic()).publishOn(Schedulers.elastic());
    }

    @GetMapping("/{id}")
    public Mono<PersonDtoResponse> findById(@PathVariable String id) {
        return personService.findById(id).subscribeOn(Schedulers.elastic()).publishOn(Schedulers.elastic());
    }
}