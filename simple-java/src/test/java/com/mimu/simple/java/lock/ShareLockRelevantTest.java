package com.mimu.simple.java.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ShareLockRelevantTest {

    @Test
    public void info() throws InterruptedException {
        int threadNum = 3;
        CountDownLatch latch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                System.out.println("thread:" + Thread.currentThread());
                try {
                    Thread.sleep(1000);
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        latch.await();
        System.out.println("over");
    }
}
