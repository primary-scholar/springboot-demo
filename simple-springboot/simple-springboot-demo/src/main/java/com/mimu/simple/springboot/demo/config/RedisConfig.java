package com.mimu.simple.springboot.demo.config;

import com.mimu.simple.springboot.demo.config.customer.AppRedisCacheManagerBuilderCustomer;
import com.mimu.simple.springboot.demo.config.customer.AppRedisCacheManagerCustomer;
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

    @Bean
    public AppRedisCacheManagerBuilderCustomer redisCacheManagerBuilderCustomer() {
        return new AppRedisCacheManagerBuilderCustomer();
    }

    @Bean
    public AppRedisCacheManagerCustomer redisCacheManagerCustomer() {
        return new AppRedisCacheManagerCustomer();
    }
}
