package org.harvan.example.fullstack.cache.producer;

import org.springframework.aop.framework.ReflectiveMethodInvocation;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
public interface CacheProducer {
    <K> Object produce(ReflectiveMethodInvocation finalMethodInvocation, Object... i);
}