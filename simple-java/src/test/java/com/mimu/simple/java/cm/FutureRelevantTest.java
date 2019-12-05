package com.mimu.simple.java.cm;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * author: mimu
 * date: 2019/7/12
 */
public class FutureRelevantTest {


    @Test
    public void executeTest(){
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
        /*try {
            future.get(1000,TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 可以确定的是 CompletableFuture.supplyAsync() 是在一个新线程中执行 某个逻辑
     * 而 CompletableFuture.whenComplete() 是在哪个新线程中执行某个逻辑 不确定
     *
     */
    @Test

    public void runAsyncInfo() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("runAsyncInfo supplyAsync thread info:" + Thread.currentThread().getName());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        }).whenComplete((integer, throwable) -> {
            if (throwable == null) {
                System.out.println("runAsyncInfo whenComplete thread info: " + Thread.currentThread().getName());
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

    @Test
    public void futureTaskTest() throws ExecutionException, InterruptedException {
        long starttime = System.currentTimeMillis();
        FutureTask<Integer> input2_futuretask = new FutureTask<>(() -> {
            Thread.sleep(3000);
            return 5;
        });
        new Thread(input2_futuretask).start();
        FutureTask<Integer> input1_futuretask = new FutureTask<>(() -> {
            Thread.sleep(2000);
            return 3;
        });
        new Thread(input1_futuretask).start();
        Integer integer2 = input2_futuretask.get();
        Integer integer1 = input1_futuretask.get();
        System.out.println(algorithm(integer1, integer2));
        long endtime = System.currentTimeMillis();
        System.out.println("用时：" + String.valueOf(endtime - starttime));
    }
    public static int algorithm(int input, int input2) {
        return input + input2;
    }
}
