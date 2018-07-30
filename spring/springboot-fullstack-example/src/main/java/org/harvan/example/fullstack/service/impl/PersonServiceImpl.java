package org.harvan.example.fullstack.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.harvan.example.fullstack.cache.CustomCacheables;
import org.harvan.example.fullstack.cache.producer.impl.KeyUtils;
import org.harvan.example.fullstack.dto.PersonDtoRequest;
import org.harvan.example.fullstack.dto.PersonDtoResponse;
import org.harvan.example.fullstack.entity.Person;
import org.harvan.example.fullstack.library.BeanMapper;
import org.harvan.example.fullstack.repository.PersonRepository;
import org.harvan.example.fullstack.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
@Service
public class PersonServiceImpl implements PersonService {
    private static final String KEY_PREFIX = "org.harvan.example.fullstack.dto.PersonDtoResponse";
    private Logger logger = LogManager.getLogger(getClass());
    private BeanMapper mapper = BeanMapper.INSTANCE;
    private PersonRepository personRepository;
    private ReactiveRedisOperations<String, Object> redisOperations;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setRedisOperations(ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public Flux<PersonDtoResponse> findAll() {
        return Flux.fromIterable(mapper.map(personRepository.findAll()));
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public Mono<PersonDtoResponse> save(PersonDtoRequest personDtoRequest) {
        return Mono.defer(() -> {
            Person person = mapper.map(personDtoRequest);
            Person personSaved = personRepository.save(person);
            PersonDtoResponse personDtoResponse = mapper.map(personSaved);

            if (logger.isDebugEnabled()) {
                logger.debug("person new: {}", person);
                logger.debug("person saved: {}", personSaved);
                logger.debug("personDtoResponse: {}", personDtoResponse);
            }

            redisOperations.opsForValue().set(KeyUtils.getKey(KEY_PREFIX, personDtoResponse.getId()), personDtoResponse).subscribe();

            return Mono.just(personDtoResponse);
        });
    }

    @CustomCacheables(value = KEY_PREFIX)
    @Override
    public Mono<PersonDtoResponse> findById(String id) {
        return findByIdValue(id);
    }

    private Mono<PersonDtoResponse> findByIdValue(String id) {
        Optional<Person> person = personRepository.findById(Long.valueOf(id));

        if (person.isPresent()) {
            return Mono.just(mapper.map(person.get()));
        } else {
            return Mono.empty();
        }
    }
}