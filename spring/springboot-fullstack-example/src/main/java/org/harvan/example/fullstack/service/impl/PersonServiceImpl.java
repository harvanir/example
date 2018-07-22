package org.harvan.example.fullstack.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.harvan.example.fullstack.repository.PersonRepository;
import org.harvan.example.fullstack.dto.PersonDtoRequest;
import org.harvan.example.fullstack.dto.PersonDtoResponse;
import org.harvan.example.fullstack.entity.Person;
import org.harvan.example.fullstack.library.BeanMapper;
import org.harvan.example.fullstack.library.RedisMapper;
import org.harvan.example.fullstack.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (22 Jul 2018)
 */
@Service
public class PersonServiceImpl implements PersonService {
    private static final String PERSON_KEY = "Person.key";
    private Logger logger = LogManager.getLogger(getClass());
    private BeanMapper mapper = BeanMapper.INSTANCE;
    private PersonRepository personRepository;
    private ReactiveRedisOperations<String, Object> redisOperations;
    private ObjectMapper objectMapper;
    private RedisMapper redisMapper;

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setRedisOperations(ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setRedisMapper(RedisMapper redisMapper) {
        this.redisMapper = redisMapper;
    }

    @Override
    public Flux<PersonDtoResponse> findAll() {
        return Flux.fromIterable(mapper.map(personRepository.findAll()));
    }

    private static final String getKey(Long id) {
        return getKey(id.toString());
    }

    private static final String getKey(String id) {
        return PERSON_KEY + id;
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

            redisOperations.opsForValue().set(getKey(personDtoResponse.getId()), personDtoResponse).subscribe();

            return Mono.just(personDtoResponse);
        });
    }

    @Override
    public Mono<PersonDtoResponse> findById(String id) {
        return redisOperations.execute(
                connection -> {
                    logger.debug("read from redis, key: {}", id);
                    return connection.stringCommands().get(ByteBuffer.wrap(getKey(id).getBytes()));
                }
        ).next().map(byteBuffer -> redisMapper.map(byteBuffer, PersonDtoResponse.class)
        ).switchIfEmpty(
                Mono.defer(() -> {
                    if (logger.isDebugEnabled()) {
                        logger.debug("read from DB, id: {}", id);
                    }
                    return Mono.just(mapper.map(personRepository.findById(Long.valueOf(id)).get()));
                })
        ).map(
                emitter ->
                {
                    redisOperations.opsForValue().set(getKey(emitter.getId()), emitter).subscribe();

                    return emitter;
                }
        );
    }
}