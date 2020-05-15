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

    /**
     * spring boot 2.0 以后 一般使用 实现 XXXCustomizer 接口 的方式 实现 自定义
     * 详见这里的 {@link AppRedisCacheManagerBuilderCustomer,AppRedisCacheManagerCustomer}
     * @return
     */
    @Bean
    public AppRedisCacheManagerBuilderCustomer redisCacheManagerBuilderCustomer() {
        return new AppRedisCacheManagerBuilderCustomer();
    }

    @Bean
    public AppRedisCacheManagerCustomer redisCacheManagerCustomer() {
        return new AppRedisCacheManagerCustomer();
    }
}
