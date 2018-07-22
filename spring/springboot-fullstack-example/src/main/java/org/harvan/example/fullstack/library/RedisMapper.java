package org.harvan.example.fullstack.library;

import java.nio.ByteBuffer;

/**
 * @author Harvan Irsyadi
 * @version 1.0.0
 * @since 1.0.0 (23 Jul 2018)
 */
public interface RedisMapper {
    <T> T map(ByteBuffer value, Class<T> targetClass);
}