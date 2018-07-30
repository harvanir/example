package org.harvan.example.fullstack.cache.producer.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.harvan.example.fullstack.cache.CustomCacheables;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
@Component
public class MonoProducer extends ReactorProducer<Mono<Object>> {
    private Logger logger = LogManager.getLogger(getClass());

    public boolean isResponsible(Method method) {
        return Mono.class.isAssignableFrom(method.getReturnType());
    }

    public Mono<Object> produce(ReflectiveMethodInvocation methodInvocation, Object... id) {
        return Mono.defer(() -> reactiveJsonObjectRedisOperations.execute(connection -> {
            CustomCacheables annotation = methodInvocation.getMethod().getAnnotation(CustomCacheables.class);
            String key = getKey(annotation, id);

            if (logger.isDebugEnabled()) {
                logger.debug("Read from redis, key: {}", key);
            }

            return connection.stringCommands().get(ByteBuffer.wrap(key.getBytes()));
        }).next().map(
                byteBuffer -> reactiveJsonObjectRedisOperations.getSerializationContext().getValueSerializationPair().read(byteBuffer)
        ).switchIfEmpty(Mono.defer(() -> createMono(methodInvocation, id)
        )).map(emitter -> {
            CustomCacheables annotation = methodInvocation.getMethod().getAnnotation(CustomCacheables.class);
            reactiveJsonObjectRedisOperations.opsForValue().set(getKey(annotation, id), emitter).subscribe();

            return emitter;
        }));
    }

    private Mono<Object> createMono(ReflectiveMethodInvocation methodInvocation, Object... id) {
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Read from direct instance method: {}", id);
            }

            return (Mono<Object>) methodInvocation.getMethod().invoke(methodInvocation.getThis(), id);
        } catch (Exception e) {
            logger.error("Error invoke direct method.", e);

            return Mono.error(e);
        }
    }
}