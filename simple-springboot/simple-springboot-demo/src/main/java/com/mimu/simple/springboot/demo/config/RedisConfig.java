package com.mimu.simple.springboot.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 author: mimu
 date: 2020/4/16
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class RedisConfig {

    /*@Bean
    public CacheManager cacheManager() {
        return null;
    }*/

}
