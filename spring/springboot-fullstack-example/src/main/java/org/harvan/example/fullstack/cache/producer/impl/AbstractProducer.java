package org.harvan.example.fullstack.cache.producer.impl;

import org.harvan.example.fullstack.cache.CustomCacheables;
import org.harvan.example.fullstack.cache.producer.Producer;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
abstract class AbstractProducer<T> implements Producer<T> {
    protected final String getKey(CustomCacheables annotation, Object... id) {
        return KeyUtils.getKey(annotation.value(), id);
    }
}
