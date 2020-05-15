package com.mimu.simple.springboot.demo.config.customizer.cache;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.data.redis.cache.RedisCacheManager;

/**
 author: mimu
 date: 2020/5/14
 */
public class AppRedisCacheManagerCustomer implements CacheManagerCustomizer<RedisCacheManager> {
    @Override
    public void customize(RedisCacheManager cacheManager) {
    }

}
