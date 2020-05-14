package com.mimu.simple.springboot.demo.config.customer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;
import java.util.Objects;

/**
 author: mimu
 date: 2020/5/14
 */
public class AppRedisCacheManagerCustomer implements CacheManagerCustomizer<RedisCacheManager> {
    @Override
    public void customize(RedisCacheManager cacheManager) {
        setCacheExpire(cacheManager, RedisTTlConstant.minute_1_info, RedisTTlConstant.minute_1_ttl);
        setCacheExpire(cacheManager, RedisTTlConstant.minute_5_info, RedisTTlConstant.minute_5_ttl);
        setCacheExpire(cacheManager, RedisTTlConstant.minute_10_info, RedisTTlConstant.minute_10_ttl);
        setSerialize(cacheManager, RedisTTlConstant.minute_1_info, getJackson2());
        setSerialize(cacheManager, RedisTTlConstant.minute_5_info, getJackson2());
        setSerialize(cacheManager, RedisTTlConstant.minute_10_info, getJackson2());
    }

    private void setCacheExpire(RedisCacheManager cacheManager, String cacheName, long expire) {
        ((RedisCache) (Objects.requireNonNull(cacheManager.getCache(cacheName)))).getCacheConfiguration().entryTtl(Duration.ofSeconds(expire));
    }

    private void setSerialize(RedisCacheManager cacheManager, String cacheName, RedisSerializer serializer) {
        ((RedisCache) (Objects.requireNonNull(cacheManager.getCache(cacheName)))).getCacheConfiguration().serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer));
    }

    public Jackson2JsonRedisSerializer<Object> getJackson2() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
}
