package org.harvan.example.fullstack.cache.producer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
abstract class ReactorProducer<T> extends AbstractProducer<T> {
    protected ReactiveRedisOperations<String, Object> reactiveJsonObjectRedisOperations;

    @Autowired
    public void setReactiveJsonObjectRedisOperations(ReactiveRedisOperations<String, Object> reactiveJsonObjectRedisOperations) {
        this.reactiveJsonObjectRedisOperations = reactiveJsonObjectRedisOperations;
    }
}