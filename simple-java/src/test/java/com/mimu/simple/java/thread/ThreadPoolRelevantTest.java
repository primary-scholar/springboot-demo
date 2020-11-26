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

    /**
     * 线程池 抛异常时的 执行清情况
     * <p>
     * 当执行方式是execute时,可以看到堆栈异常的输出。
     * 当执行方式是submit时,堆栈异常没有输出。但是调用Future.get()方法时，可以捕获到异常。
     * 不会影响线程池里面其他线程的正常执行。
     * 线程池会把这个线程移除掉，并创建一个新的线程放到线程池中。 该逻辑在 runWorker() 中
     *
     * @param num
     */
    private static void sayHi(int num) {
        System.out.println(Thread.currentThread());
        if (num % 2 == 0)
            throw new RuntimeException(Thread.currentThread() + " 异常 ");

    }

}
