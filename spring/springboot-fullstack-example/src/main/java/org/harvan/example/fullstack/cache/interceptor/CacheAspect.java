package org.harvan.example.fullstack.cache.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.harvan.example.fullstack.cache.CacheException;
import org.harvan.example.fullstack.cache.CustomCacheable;
import org.harvan.example.fullstack.cache.CustomEvict;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * To ensure this interceptor is highest order than other interceptors, the Order is set to 0 (zero).
 *
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (29 Jul 2018)
 */
@Aspect
@Order(0)
public class CacheAspect {
    private Map<Class, ReflectiveMethodInvocation> methodMap = new ConcurrentHashMap<>();
    private CacheDelegator cacheDelegator;

    public CacheAspect(CacheDelegator cacheDelegator) {
        this.cacheDelegator = cacheDelegator;
    }

    private Field getField(Field[] fields, String name) {
        for (Field field : fields) {
            if (name.equals(field.getName())) {
                return field;
            }
        }

        return null;
    }

    private ReflectiveMethodInvocation getMethodInvocation(ProceedingJoinPoint proceedingJoinPoint) throws IllegalAccessException {
        MethodInvocationProceedingJoinPoint joinPoint = (MethodInvocationProceedingJoinPoint) proceedingJoinPoint;
        Signature signature = proceedingJoinPoint.getSignature();
        Class returnType = ((MethodSignature) signature).getReturnType();
        ReflectiveMethodInvocation methodInvocation = methodMap.get(returnType);

        if (methodInvocation == null) {
            Class<?> clazz = proceedingJoinPoint.getClass();
            Field field = getField(clazz.getDeclaredFields(), "methodInvocation");

            if (field != null) {
                field.setAccessible(true);
                methodInvocation = (ReflectiveMethodInvocation) field.get(joinPoint);
                methodMap.put(clazz, methodInvocation);
            } else {
                throw new CacheException("No methodInvocation found.");
            }
        }

        return methodInvocation;
    }

    @Around("@annotation(customCacheable)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, CustomCacheable customCacheable) throws IllegalAccessException {
        final ReflectiveMethodInvocation methodInvocation = getMethodInvocation(proceedingJoinPoint);

        return cacheDelegator.delegate(customCacheable, methodInvocation.getThis(), methodInvocation.getMethod(), proceedingJoinPoint.getArgs());
    }

    @Around("@annotation(customEvict)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, CustomEvict customEvict) throws IllegalAccessException {
        final ReflectiveMethodInvocation methodInvocation = getMethodInvocation(proceedingJoinPoint);

        return cacheDelegator.delegate(customEvict, methodInvocation.getThis(), methodInvocation.getMethod(), proceedingJoinPoint.getArgs());
    }
}