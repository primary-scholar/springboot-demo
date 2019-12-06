package com.mimu.simple.java.lock;


/**
 author: mimu
 date: 2019/12/6
 */
public class LockRelevantTest {

    /**
     * 在JUnit的@Test方法中启用多线程，新启动的线程会随着@Test主线程的结束而结束
     * 即 info2() 方法执行完毕 其开启的 两个线程同时也会执行完毕
     */
    public void info2() {
        LockRelevant.InnerLock2 innerLock2 = new LockRelevant.InnerLock2(false);
        new Thread(() -> {
            while (true) {
                innerLock2.printA();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                innerLock2.printB();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void info1() {
        LockRelevant.InnerLock1 innerLock1 = new LockRelevant.InnerLock1(false);
        new Thread(() -> {
            while (true) {
                innerLock1.printA();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                innerLock1.printA();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        LockRelevantTest test = new LockRelevantTest();
        test.info2();
    }

}