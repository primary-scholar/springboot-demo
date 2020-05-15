package com.mimu.simple.springboot.demo.config.customer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


/**
 author: mimu
 date: 2020/5/14
 */
public class AppRedisCacheManagerBuilderCustomer implements RedisCacheManagerBuilderCustomizer {

    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
        Map<String, RedisCacheConfiguration> customCacheConfigMap = new HashMap<>();
        RedisCacheConfiguration minute_1_info = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMillis(RedisTTlConstant.minute_1_ttl))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(getJackson2()));
        RedisCacheConfiguration minute_5_info = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMillis(RedisTTlConstant.minute_5_ttl))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(getJackson2()));
        RedisCacheConfiguration minute_10_info = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMillis(RedisTTlConstant.minute_10_ttl))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(getJackson2()));
        customCacheConfigMap.put(RedisTTlConstant.minute_1_info, minute_1_info);
        customCacheConfigMap.put(RedisTTlConstant.minute_5_info, minute_5_info);
        customCacheConfigMap.put(RedisTTlConstant.minute_10_info, minute_10_info);
        builder.withInitialCacheConfigurations(customCacheConfigMap);
    }


    private Jackson2JsonRedisSerializer<Object> getJackson2() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }
}
