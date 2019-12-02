package com.mimu.simple.comn.zookeeper;

import org.junit.Test;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * author: mimu
 * date: 2019/10/27
 */
public class SimpleZKOperatorTest {
    private static final String path = "/abc", data = "abc";

    @Test
    public void info() throws Exception {
        System.out.println(CuratorCRUDOperator.createPNode(path, data));
        System.out.println(CuratorCRUDOperator.readPNode(path));
        System.out.println(CuratorCRUDOperator.deletePNode(path));
    }

    @Test
    public void dl() throws Exception {
        Executor executor = Executors.newFixedThreadPool(3);
        CuratorDLOperator curatorDLOperator = new CuratorDLOperator(CuratorCRUDOperator.getClient(), "/curator/dlocks");

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("getLock: " + curatorDLOperator.getLock(30, TimeUnit.MILLISECONDS));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("releaseLock: " + curatorDLOperator.releaseLock());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        ((ExecutorService) executor).shutdown();
    }
}