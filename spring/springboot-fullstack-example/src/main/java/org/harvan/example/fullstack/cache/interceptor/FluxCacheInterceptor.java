package org.harvan.example.fullstack.cache.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.function.Supplier;

import static reactor.core.scheduler.Schedulers.elastic;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (12 Aug 2018)
 */
public class FluxCacheInterceptor extends ReactorCacheInterceptor<Flux<Object>> {
    private Logger logger = LogManager.getLogger(getClass());

    public FluxCacheInterceptor(ReactiveRedisTemplate<String, Object> reactiveRedisTemplate) {
        super(reactiveRedisTemplate);
    }

    public boolean isResponsible(Method method) {
        return Flux.class.isAssignableFrom(method.getReturnType());
    }

    @Override
    public Flux<Object> getCache(Supplier<Flux<Object>> supplier, String key, Object... args) {
        return Flux.defer(() -> reactiveRedisTemplate.execute(connection -> {
            String parsedKey = getKey(key, args);

            if (logger.isDebugEnabled()) {
                logger.debug("Read from redis, key: {}", parsedKey);
            }

            return connection.stringCommands().get(ByteBuffer.wrap(parsedKey.getBytes()));
        }).subscribeOn(elastic()).publishOn(elastic()).flatMapIterable(
                byteBuffer -> (List<Object>) reactiveRedisTemplate.getSerializationContext().getValueSerializationPair()
                        .read(byteBuffer)
        ).switchIfEmpty(Flux.defer(() ->
                put(key, supplier, args)
        )));
    }

    private Flux<Object> put(String key, Supplier<Flux<Object>> supplier, Object... args) {
        return supplier.get().collectList().flatMap(emitter ->
                reactiveRedisTemplate.opsForValue().set(getKey(key, args), emitter
                ).map(aBoolean ->
                        emitter
                )
        ).flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Boolean> evict(String value, Object... args) {
        return reactiveRedisTemplate.opsForValue().delete(getKey(value, args)).subscribeOn(elastic()).publishOn(elastic());
    }
}