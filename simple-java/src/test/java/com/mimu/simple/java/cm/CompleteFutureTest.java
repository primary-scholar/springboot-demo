package com.mimu.simple.java.cm;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * author: mimu
 * date: 2019/7/12
 */
public class CompleteFutureTest {

    public static void main(String[] args) {
        while (true){
            System.out.println("start");
            for (int i=0;i<10000;i++){
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

    public static void execute(){
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
}
