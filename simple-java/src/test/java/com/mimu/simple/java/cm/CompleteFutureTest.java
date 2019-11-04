package com.mimu.simple.java.cm;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * author: mimu
 * date: 2019/7/12
 */
public class CompleteFutureTest {

    public static void main(String[] args) {
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

    @Test
    public void info() {
        CompletableFuture<Object> completableFuture = new CompletableFuture<>();
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
}
