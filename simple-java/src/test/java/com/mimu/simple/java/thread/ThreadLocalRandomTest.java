package com.mimu.simple.java.thread;

import java.util.concurrent.ThreadLocalRandom;

/**
 * author: mimu
 * date: 2020/2/28
 */
public class ThreadLocalRandomTest {

    private static final ThreadLocalRandom RANDOM =
            ThreadLocalRandom.current();

    public static void main(String[] args) {
        int loop = 2;
        for (int i = 0; i < loop; i++) {
            new Player().start();
        }
        for (int i = 0; i < loop; i++) {
            new Player1().start();
        }
    }

    /**
     * 这里 Random  在所有线程中 输出的 数字 都一样
     */
    private static class Player extends Thread {
        @Override
        public void run() {
            System.out.println(getName() + ": " + RANDOM.nextInt(100));
        }
    }

    private static class Player1 extends Thread {
        @Override
        public void run() {
            System.out.println(getName() + ": " + ThreadLocalRandom.current().nextInt(100));
        }
    }

}
