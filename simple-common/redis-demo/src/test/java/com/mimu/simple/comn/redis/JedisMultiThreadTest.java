package com.mimu.simple.comn.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 author: mimu
 date: 2020/4/17
 */
@Slf4j
public class JedisMultiThreadTest {
    private ExecutorService service = Executors.newFixedThreadPool(1000);

    public void info() {
        Jedis jedis = new Jedis("127.0.0.1", 6379, 1000);
        for (int i = 0; i < 100; i++) {
            service.execute(new JedisSetEvent(jedis));
        }
    }

    public void info1() {
        for (int i = 0; i < 10000; i++) {
            service.execute(new JedisSetOEvent());
        }
    }

    public static void main(String[] args) {
        JedisMultiThreadTest threadTest = new JedisMultiThreadTest();
        threadTest.info1();
    }

    public static class JedisSetEvent implements Runnable {
        private Jedis jedis;

        public JedisSetEvent(Jedis jedis) {
            this.jedis = jedis;
        }

        @Override
        public void run() {
            while (true) {
                jedis.set("aaa", "aaa");
                jedis.close();
            }
        }
    }

    public static class JedisSetOEvent implements Runnable {

        public JedisSetOEvent() {
        }

        @Override
        public void run() {
            Jedis jedis = new Jedis("127.0.0.1", 6379, 1000);
            jedis.set("aaa", "aaa");
            jedis.close();
            log.info("ok");
        }
    }


}
