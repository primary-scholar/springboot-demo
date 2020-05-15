package com.mimu.simple.springboot.demo.config;

import com.mimu.simple.springboot.demo.config.customizer.cache.AppRedisCacheManagerBuilderCustomer;
import com.mimu.simple.springboot.demo.config.customizer.cache.AppRedisCacheManagerCustomer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;

/**
 author: mimu
 date: 2020/4/16
 */
@Configuration
@EnableCaching(proxyTargetClass = true)
public class AppRedisConfig {

    /**
     * spring boot 2.0 以后 一般使用 实现 XXXCustomizer 接口 的方式 实现 自定义
     * 详见这里的 {@link AppRedisCacheManagerBuilderCustomer,AppRedisCacheManagerCustomer}
     * @return
     */
    @Bean
    @ConditionalOnBean(RedisCache.class)
    public AppRedisCacheManagerBuilderCustomer redisCacheManagerBuilderCustomer() {
        return new AppRedisCacheManagerBuilderCustomer();
    }

    @Bean
    @ConditionalOnBean(RedisCache.class)
    public AppRedisCacheManagerCustomer redisCacheManagerCustomer() {
        return new AppRedisCacheManagerCustomer();
    }
}
