package com.mimu.simple.java.thread;


import java.util.concurrent.*;

public class ThreadPoolRelevantTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        threadPool.execute(() -> sayHi(1));
        System.out.println(threadPool);
        Future<?> submit = threadPool.submit(() -> sayHi(2));
        System.out.println(threadPool);
        threadPool.execute(() -> sayHi(3));
        System.out.println(threadPool);
        threadPool.execute(() -> sayHi(4));
        System.out.println(threadPool);
        System.out.println(submit.get());
        threadPool.shutdown();
    }

    private static void sayHi(int num) {
        System.out.println(Thread.currentThread());
        if (num % 2 == 0) {
            throw new RuntimeException(Thread.currentThread() + " 异常 ");
        }
    }

}
