package org.harvan.example.fullstack.cache.producer.impl;

import org.harvan.example.fullstack.cache.CacheException;
import org.harvan.example.fullstack.cache.producer.CacheProducer;
import org.harvan.example.fullstack.cache.producer.Producer;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
@Component
public class ReactorCacheProducer implements CacheProducer {
    private static final List<Producer<?>> producerList = new ArrayList<>();
    private MonoProducer monoProducer;

    @Autowired
    public void setMonoProducer(MonoProducer monoProducer) { // NOSONAR
        this.monoProducer = monoProducer;
    }

    static <T> Producer<T> getProducer(Method method) {
        for (Producer<?> producer : producerList) {
            if (producer.isResponsible(method)) {
                return (Producer<T>) producer;
            }
        }

        throw new CacheException("No producer found.");
    }

    @Override
    public <K> Object produce(ReflectiveMethodInvocation finalMethodInvocation, Object... i) {
        Producer<Object> producer = getProducer(finalMethodInvocation.getMethod());

        return producer.produce(finalMethodInvocation, i);
    }

    @PostConstruct
    public void init() { // NOSONAR
        producerList.add(monoProducer);
    }
}