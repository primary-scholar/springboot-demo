package com.mimu.simple.java.thread;

import java.util.UUID;
import java.util.concurrent.*;

public class ThreadLambdaTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread abc = new Thread(() -> System.out.println("abc"));
        abc.start();
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
