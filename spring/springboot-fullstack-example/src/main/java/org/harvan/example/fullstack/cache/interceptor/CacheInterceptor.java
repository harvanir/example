package org.harvan.example.fullstack.cache.interceptor;

import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.function.Supplier;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
public interface CacheInterceptor<T> {
    boolean isResponsible(Method method);

    T getCache(Supplier<T> supplier, String key, Object... args);

    T getCache(Supplier<T> supplier, String key, Duration timeout, Object... args);

    Mono<Boolean> evict(String key, Object... args);
}