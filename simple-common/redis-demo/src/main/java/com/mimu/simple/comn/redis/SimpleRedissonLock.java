package com.mimu.simple.comn.redis;

public class SimpleRedissonLock {

    private static final RedissonClient redissonClient;

    static {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress()
                .setTimeout()
                .setConnectionPoolSize()
                .setConnectionMinimumIdleSize();
        redissonClient = Redisson.create(config);
    }


}
