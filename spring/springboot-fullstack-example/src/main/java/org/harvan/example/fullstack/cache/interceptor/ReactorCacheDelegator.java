package org.harvan.example.fullstack.cache.interceptor;

import org.harvan.example.fullstack.cache.CacheException;
import org.harvan.example.fullstack.cache.CustomCacheable;
import org.harvan.example.fullstack.cache.CustomEvict;
import org.springframework.beans.factory.InitializingBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (30 Jul 2018)
 */
public class ReactorCacheDelegator implements CacheDelegator, InitializingBean {
    private static final List<CacheInterceptor<?>> CACHE_INTERCEPTORS = new ArrayList<>();
    private CacheInterceptor<Mono<Object>> monoCacheInterceptor;
    private CacheInterceptor<Flux<Object>> fluxCacheInterceptor;

    public ReactorCacheDelegator(CacheInterceptor<Mono<Object>> monoCacheInterceptor, CacheInterceptor<Flux<Object>> fluxCacheInterceptor) {
        this.monoCacheInterceptor = monoCacheInterceptor;
        this.fluxCacheInterceptor = fluxCacheInterceptor;
    }

    private CacheInterceptor<Object> getInterceptor(Method method) {
        for (CacheInterceptor<?> cacheInterceptor : CACHE_INTERCEPTORS) {
            if (cacheInterceptor.isResponsible(method)) {
                return (CacheInterceptor<Object>) cacheInterceptor;
            }
        }

        throw new CacheException("No interceptor found.");
    }

    @Override
    public Object delegate(Annotation annotation, Object realObject, Method method, Object... args) {
        CacheInterceptor<Object> cacheInterceptor = getInterceptor(method);

        if (annotation instanceof CustomCacheable) {
            return cacheInterceptor.getCache(
                    () -> invoke(realObject, method, args),
                    ((CustomCacheable) annotation).key(),
                    args
            );
        }
        cacheInterceptor.evict(((CustomEvict) annotation).key(), args).subscribe();
        return invoke(realObject, method, args);
    }

    private Object invoke(Object realObject, Method method, Object... args) {
        try {
            return method.invoke(realObject, args);
        } catch (Exception e) {
            throw new CacheException("Failed invoke.", e);
        }
    }

    @Override
    public void afterPropertiesSet() {
        CACHE_INTERCEPTORS.add(monoCacheInterceptor);
        CACHE_INTERCEPTORS.add(fluxCacheInterceptor);
    }
}