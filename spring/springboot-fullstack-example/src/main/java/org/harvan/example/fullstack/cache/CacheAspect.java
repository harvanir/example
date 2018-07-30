package org.harvan.example.fullstack.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.harvan.example.fullstack.cache.producer.CacheProducer;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * This class will invoke real method implementation, make sure no proxy object is expected to be called.
 *
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (29 Jul 2018)
 */
@Aspect
@Component
public class CacheAspect {
    private static final String ANNOTATION = "@annotation(org.harvan.example.fullstack.cache.CustomCacheables)";
    private Logger logger = LogManager.getLogger(getClass());
    private Map<Class, ReflectiveMethodInvocation> methodMap = new ConcurrentHashMap<>();
    private CacheProducer cacheProducer;

    @Autowired
    public void setCacheProducer(CacheProducer cacheProducer) {
        this.cacheProducer = cacheProducer;
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
        ReflectiveMethodInvocation methodInvocation = methodMap.get(proceedingJoinPoint.getClass());

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

    @Pointcut(ANNOTATION + " && args(id)")
    public void pointCut1(Object id) {
        // advice point cut
    }

    @Pointcut(ANNOTATION + " && args(id1, id2)")
    public void pointCut2(Object id1, Object id2) {
        // advice point cut2
    }

    @Pointcut(ANNOTATION + " && args(id1, id2, id3)")
    public void pointCut3(Object id1, Object id2, Object id3) {
        // advice point cut3
    }

    @Before("pointCut1(Object) && args(id)")
    public void before1(JoinPoint joinPoint, Object id) {
        if (logger.isDebugEnabled()) {
            logger.debug("Intercept before1 method: {}.{}",
                    joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
        }
    }

    @Around("pointCut1(Object) && args(id)")
    public Object after(ProceedingJoinPoint proceedingJoinPoint, Object id) throws IllegalAccessException {
        final ReflectiveMethodInvocation finalMethodInvocation = getMethodInvocation(proceedingJoinPoint);

        return cacheProducer.produce(finalMethodInvocation, id);
    }

    @AfterReturning(pointcut = "pointCut1(Object)", returning = "result")
    public void doAfterReturnResponse(JoinPoint joinPoint, Object result) {
        if (logger.isDebugEnabled()) {
            logger.debug("Returning doAfterReturnResponse value: {}", result);
        }
    }

    @Before("pointCut2(Object, Object) && args(id1, id2)")
    public void before2(JoinPoint joinPoint, Object id1, Object id2) {
        if (logger.isDebugEnabled()) {
            logger.debug("Intercept before2 method: {}.{}",
                    joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
        }
    }

    @Around("pointCut2(Object, Object) && args(id1, id2)")
    public Object after2(ProceedingJoinPoint proceedingJoinPoint, Object id1, Object id2) throws IllegalAccessException {
        final ReflectiveMethodInvocation finalMethodInvocation = getMethodInvocation(proceedingJoinPoint);

        return cacheProducer.produce(finalMethodInvocation, id1, id2);
    }

    @AfterReturning(pointcut = "pointCut2(Object, Object)", returning = "result")
    public void doAfterReturnResponse2(JoinPoint joinPoint, Object result) {
        if (logger.isDebugEnabled()) {
            logger.debug("Returning doAfterReturnResponse2 value: {}", result);
        }
    }

    @Before("pointCut3(Object, Object, Object) && args(id1, id2, id3)")
    public void before3(JoinPoint joinPoint, Object id1, Object id2, Object id3) {
        if (logger.isDebugEnabled()) {
            logger.debug("Intercept before3 method: {}.{}",
                    joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
        }
    }

    @Around("pointCut3(Object, Object, Object) && args(id1, id2, id3)")
    public Object after3(ProceedingJoinPoint proceedingJoinPoint, Object id1, Object id2, Object id3) throws IllegalAccessException {
        final ReflectiveMethodInvocation finalMethodInvocation = getMethodInvocation(proceedingJoinPoint);

        return cacheProducer.produce(finalMethodInvocation, id1, id2, id3);
    }

    @AfterReturning(pointcut = "pointCut3(Object, Object, Object)", returning = "result")
    public void doAfterReturnResponse3(JoinPoint joinPoint, Object result) {
        if (logger.isDebugEnabled()) {
            logger.debug("Returning doAfterReturnResponse3 value: {}", result);
        }
    }
}