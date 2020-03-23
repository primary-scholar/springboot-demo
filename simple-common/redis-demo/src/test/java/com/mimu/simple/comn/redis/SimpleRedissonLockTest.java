package com.mimu.simple.comn.redis;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 author: mimu
 date: 2020/3/23
 */
public class SimpleRedissonLockTest {

    Executor executor = Executors.newFixedThreadPool(2);
    String lockKey = "lockKey";

    @Test
    public void tryLock() {
    }
}