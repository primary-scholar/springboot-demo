package com.mimu.simple.springboot.demo.config.customer;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.data.redis.cache.RedisCacheManager;


/**
 author: mimu
 date: 2020/5/14
 */
public class AppRedisCacheManagerBuilderCustomer implements RedisCacheManagerBuilderCustomizer {

    @Override
    public void customize(RedisCacheManager.RedisCacheManagerBuilder builder) {
    }



    enum RedisCacheTTLEnum {

        MINUTE_1("one_minute", 60 * 1000), MINURW_5("five_minute", 5 * 60 * 1000), MINUTE_10("ten_minute", 10 * 60 * 1000);

        private String desc;
        private int ttl;

        RedisCacheTTLEnum(String desc, int time) {
            this.desc = desc;
            this.ttl = time;
        }

        public int getTtl() {
            return ttl;
        }
    }
}
