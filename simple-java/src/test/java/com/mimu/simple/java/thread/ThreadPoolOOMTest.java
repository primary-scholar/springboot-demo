package com.mimu.simple.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolOOMTest {
    private static final int size = 1000;
    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws InterruptedException {
        /*while (true){
            ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();
            Runnable runnable = () -> threadLocal.set(ContentFillUtil.fillContent());
            Thread thread = new Thread(runnable);
            System.out.println("thread " + thread.getName());
            thread.start();
            Thread.sleep(10);
        }*/
        while (true) {
            executor.execute(() -> {
                ThreadLocal<byte[]> threadLocal = new ThreadLocal<>();
                threadLocal.set(ContentFillUtil.fillContent());
                System.out.println("thread " + Thread.currentThread().getName());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadLocal=null;
            });
        }

    }


    static class ContentFillUtil {

        public static byte[] fillContent() {
            byte[] content = new byte[size];
            for (int i = 0; i < size; i++) {
                content[i] = 'a';
            }
            return content;
        }
    }
}
