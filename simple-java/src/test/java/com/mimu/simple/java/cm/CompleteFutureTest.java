package com.mimu.simple.java.cm;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.function.BiConsumer;
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
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    @Test
    public void runAsyncInfo() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("runAsyncInfo supplyAsync thread info:" + Thread.currentThread().getName());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        }).whenCompleteAsync((integer, throwable) -> {
            System.out.println("runAsyncInfo whenComplete thread info: " + Thread.currentThread().getName());
            System.out.println(integer);
        });
        System.out.println("runAsyncInfo outer thread info:" + Thread.currentThread().getName());
        Integer integer = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println(integer);
    }

    @Test
    public void runAsyncInfo1() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Object> future = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("runAsyncInfo supplyAsync thread info:" + Thread.currentThread().getName());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }).whenCompleteAsync((object, throwable) -> {
            System.out.println("runAsyncInfo whenComplete thread info: " + Thread.currentThread().getName());
            System.out.println(object);
        });
        System.out.println("runAsyncInfo outer thread info:" + Thread.currentThread().getName());
        Object object = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println(object);
    }

    @Test
    public void runAsyncInfoContrast() throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("runAsyncInfoContrast supplyAsync thread info:" + Thread.currentThread().getName());
            return 10;
        }).whenCompleteAsync((integer, throwable) -> {
            System.out.println("runAsyncInfoContrast whenComplete thread info: " + Thread.currentThread().getName());
            System.out.println(integer);
        });
        System.out.println("runAsyncInfoContrast outer thread info:" + Thread.currentThread().getName());
        Integer integer = future.get(100, TimeUnit.MILLISECONDS);
        System.out.println(integer);
    }
}
