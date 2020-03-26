package com.mimu.simple.comn.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 author: mimu
 date: 2020/3/23
 */
@Slf4j
public class SimpleRedissonLockTest {

    Executor executor = Executors.newFixedThreadPool(2);
    String lockKey = "lockKey";

    @Test
    public void unloak(){
        SimpleRedissonLock.lock(lockKey);
        SimpleRedissonLock.unlock(lockKey);
    }

    @Test
    public void info(){
        SimpleRedissonLock.tryLock(lockKey,1,3);
        SimpleRedissonLock.unlock(lockKey);
    }

    @Test
    public void tryLock() {
        RedissonLockEvent event = new RedissonLockEvent(lockKey,1,3);
        RedissonLockEvent event1 = new RedissonLockEvent(lockKey,1,3);
        executor.execute(event);
        executor.execute(event1);
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Slf4j
    public static class RedissonLockEvent implements Runnable {
        private String lockKey;
        private int waiteTime;
        private int leaseTime;

        RedissonLockEvent(String path, int waiteTime, int leaseTime) {
            this.lockKey = path;
            this.waiteTime = waiteTime;
            this.leaseTime = leaseTime;
        }

        @Override
        public void run() {
            boolean b = SimpleRedissonLock.tryLock(lockKey, waiteTime, leaseTime);
            log.info("get lock result={}", b);
        }
    }
}