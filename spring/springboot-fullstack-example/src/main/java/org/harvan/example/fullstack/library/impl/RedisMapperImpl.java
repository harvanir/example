package org.harvan.example.fullstack.library.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.harvan.example.fullstack.library.RedisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (23 Jul 2018)
 */
@Service
public class RedisMapperImpl implements RedisMapper {

    private ReactiveRedisOperations<String, Object> redisOperations;
    private ObjectMapper objectMapper;

    @Autowired
    public void setRedisOperations(ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T map(ByteBuffer byteBuffer, Class<T> targetClass) {
        return objectMapper.convertValue(
                redisOperations.getSerializationContext().getValueSerializationPair().read(byteBuffer),
                targetClass);
    }
}