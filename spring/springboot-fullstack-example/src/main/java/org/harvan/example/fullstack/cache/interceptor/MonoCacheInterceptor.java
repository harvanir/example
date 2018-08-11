package org.harvan.example.fullstack.cache.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.function.Supplier;

import static reactor.core.scheduler.Schedulers.elastic;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
public class MonoCacheInterceptor extends ReactorCacheInterceptor<Mono<Object>> {
    private Logger logger = LogManager.getLogger(getClass());

    public MonoCacheInterceptor(ReactiveRedisTemplate<String, Object> reactiveRedisTemplate) {
        super(reactiveRedisTemplate);
    }

    public boolean isResponsible(Method method) {
        return Mono.class.isAssignableFrom(method.getReturnType());
    }

    @Override
    public Mono<Object> getCache(Supplier<Mono<Object>> supplier, String key, Object... args) {
        return Mono.defer(() -> reactiveRedisTemplate.execute(connection -> {
            String parsedKey = getKey(key, args);

            if (logger.isDebugEnabled()) {
                logger.debug("Read from redis, key: {}", parsedKey);
            }

            return connection.stringCommands().get(ByteBuffer.wrap(parsedKey.getBytes()));
        }).subscribeOn(elastic()).publishOn(elastic()).next().map(
                byteBuffer -> reactiveRedisTemplate.getSerializationContext().getValueSerializationPair()
                        .read(byteBuffer)
        ).switchIfEmpty(Mono.defer(() ->
                put(key, supplier, args)
        )));
    }

    private Mono<Object> put(String value, Supplier<Mono<Object>> supplier, Object... args) {
        return supplier.get().flatMap(emitter ->
                reactiveRedisTemplate.opsForValue().set(getKey(value, args), emitter
                ).map(aBoolean ->
                        emitter
                )
        );
    }

    @Override
    public Mono<Boolean> evict(String value, Object... args) {
        return reactiveRedisTemplate.opsForValue().delete(getKey(value, args)).subscribeOn(elastic()).publishOn(elastic());
    }
}