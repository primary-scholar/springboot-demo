package com.mimu.simple.comn.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.redisson.api.RLock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

/**
 * author: mimu
 * date: 2020/3/23
 */
@Slf4j
public class SimpleRedissonLockTest {

    Executor executor = Executors.newFixedThreadPool(2);
    String lockKey = "lockKey";

    @Test
    public void unloak() {
        SimpleRedissonLock.lock(lockKey);
        //SimpleRedissonLock.unlock(lockKey);
    }

    @Test
    public void info() {
        SimpleRedissonLock.tryLock(lockKey, 1, 3);
        SimpleRedissonLock.unlock(lockKey);
    }

    @Test
    public void lock() {
        LockEvent event = new LockEvent(lockKey);
        LockEvent event1 = new LockEvent(lockKey);
        executor.execute(event);
        executor.execute(event1);
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tryLock() {
        TryLockEvent event = new TryLockEvent(lockKey, 1, 3);
        TryLockEvent event1 = new TryLockEvent(lockKey, 1, 3);
        executor.execute(event);
        executor.execute(event1);
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class LockEvent implements Runnable {
        private String lockKey;

        public LockEvent(String lock) {
            this.lockKey = lock;
        }

        @Override
        public void run() {
            RLock lock = SimpleRedissonLock.lock(this.lockKey);
            try {
                lock.lockInterruptibly();
                System.out.println("thread" + Thread.currentThread() + "get lock result=true");
            } catch (InterruptedException e) {
                System.out.println("thread" + Thread.currentThread() + "get lock result=false");
            }
        }
    }

    @Slf4j
    public static class TryLockEvent implements Runnable {
        private String lockKey;
        private int waiteTime;
        private int leaseTime;

        TryLockEvent(String path, int waiteTime, int leaseTime) {
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