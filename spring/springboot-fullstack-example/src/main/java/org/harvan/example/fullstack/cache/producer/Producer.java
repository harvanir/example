package org.harvan.example.fullstack.cache.producer;

import org.springframework.aop.framework.ReflectiveMethodInvocation;

import java.lang.reflect.Method;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
public interface Producer<T> {
    boolean isResponsible(Method method);

    T produce(ReflectiveMethodInvocation finalMethodInvocation, Object... id);
}