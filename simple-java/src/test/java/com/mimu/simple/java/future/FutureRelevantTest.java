package com.mimu.simple.java.future;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * author: mimu
 * date: 2019/7/12
 */
public class FutureRelevantTest {
    private static final Logger logger = LoggerFactory.getLogger(FutureRelevantTest.class);

    @Test
    public void executeTest() {
        while (true) {
            System.out.println("start");
            for (int i = 0; i < 10000; i++) {
                execute();
            }
            try {
                System.out.println("over");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void execute() {
        CompletableFuture future = CompletableFuture.supplyAsync((Supplier<Object>) () -> "abc");
    }

    private void performTask(String stage) {
        logger.info("start stage:{},thread:{}", stage, Thread.currentThread().getName());
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("end stage:{},thread:{}", stage, Thread.currentThread().getName());
    }

    /**
     * 理论上来说: 使用 RunAsync(),SupplyAsync() 的方法其执行逻辑是在新线程中
     * 后续的 non-async() 的方法的执行逻辑 在前一阶段的 线程中执行
     * 后续的 async() 的方法的执行逻辑 是在新线程中
     */
    @Test
    public void performTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CompletableFuture.runAsync(() -> performTask("first stage"), executorService)
                .thenRun(() -> performTask("second stage"))
                .thenRunAsync(() -> performTask("third stage"), executorService)
                .join();
        logger.info("main exiting");
        executorService.shutdown();
    }

    public void performTest3() {
        logger.info("performTest3 traceing Info");
        Thread lalal = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    logger.info("traceing Info");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        lalal.start();
    }

    @Test
    public void performTest1() {
        CompletableFuture.runAsync(() -> performTask("first stage"))
                .thenRunAsync(() -> performTask("second stage"))
                .thenRun(() -> performTask("third stage"))
                .join();
        logger.info("main exiting");
    }

    @Test
    public void runAsyncInfo() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                logger.info("runAsyncInfo supplyAsync thread info:{}", Thread.currentThread().getName());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        }).whenComplete((integer, throwable) -> {
            if (throwable == null) {
                logger.info("runAsyncInfo whenComplete thread info:{}", Thread.currentThread().getName());
                System.out.println(integer);
            } else {
                System.out.println("runAsyncInfo exception occur" + throwable.getMessage());
            }
        }).exceptionally(throwable -> {
            System.out.println("runAsyncInfo exception occur" + throwable.getMessage());
            return 1;
        });
        System.out.println("runAsyncInfo outer thread info:" + Thread.currentThread().getName());
        Integer join = future.join();
        System.out.println(join);
    }

    @Test
    public void runAsyncInfoContrast() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("runAsyncInfoContrast supplyAsync thread info:" + Thread.currentThread().getName());
            return 10;
        }).whenCompleteAsync((integer, throwable) -> {
            if (throwable == null) {
                System.out.println("runAsyncInfoContrast whenComplete thread info: " + Thread.currentThread().getName());
                System.out.println(integer);
            } else {
                System.out.println("runAsyncInfoContrast exception occur" + throwable.getMessage());
            }
        }).exceptionally(throwable -> {
            System.out.println("runAsyncInfoContrast exception occur" + throwable.getMessage());
            return 1;
        });
        System.out.println("runAsyncInfoContrast outer thread info:" + Thread.currentThread().getName());
        Integer join = future.join();
        System.out.println(join);
    }

    @Test
    public void runAsyncInfoContrast1() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("runAsyncInfoContrast1 supplyAsync thread info:" + Thread.currentThread().getName());
            return 10;
        }).whenComplete((integer, throwable) -> {
            if (throwable == null) {
                System.out.println("runAsyncInfoContrast1 whenComplete thread info: " + Thread.currentThread().getName());
                System.out.println(integer);
            } else {
                System.out.println("runAsyncInfoContrast1 exception occur" + throwable.getMessage());
            }
        }).exceptionally(throwable -> {
            System.out.println("runAsyncInfoContrast1 exception occur" + throwable.getMessage());
            return 1;
        });
        System.out.println("runAsyncInfoContrast1 outer thread info:" + Thread.currentThread().getName());
        System.out.println(future);
        System.out.println(future.get(20, TimeUnit.MILLISECONDS));
        System.out.println(future);
    }

    @Test
    public void info1() {
        try {
            System.out.println(getResult().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public CompletableFuture<Integer> getResult() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("runAsyncInfo supplyAsync thread info:" + Thread.currentThread().getName());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
        return future.thenApply(Function.identity());
    }

    /**
     * Future 接口提供了一种异步 获取结果的机制，即Future 的callable<>的逻辑执行在其他线程中完成
     * 在使用 future.get() 方法之前，要确保 callable<> 的逻辑已经执行，否则get() 方法会一直阻塞
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void futureTaskTest() throws ExecutionException, InterruptedException {
        long starttime = System.currentTimeMillis();
        FutureTask<Integer> input2Futuretask = new FutureTask<>(() -> {
            Thread.sleep(2000);
            return 5;
        });
        new Thread(input2Futuretask).start();
        FutureTask<Integer> input1Futuretask = new FutureTask<>(() -> {
            Thread.sleep(1000);
            return 3;
        });
        /**
         * 此处没有 执行start() 方法 get() 会一直阻塞
         */
        new Thread(input1Futuretask).start();
        System.out.println(input1Futuretask.isDone());
        Integer integer2 = input2Futuretask.get();
        Integer integer1 = input1Futuretask.get();
        System.out.println(algorithm(integer1, integer2));
        System.out.println("用时：" + (System.currentTimeMillis() - starttime));
    }

    public static int algorithm(int input, int input2) {
        return input + input2;
    }

    public static void main(String[] args) {
        new FutureRelevantTest().performTest3();
    }
}
