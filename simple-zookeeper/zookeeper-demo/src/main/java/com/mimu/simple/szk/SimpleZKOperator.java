package com.mimu.simple.szk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.locks.Lock;

/**
 * author: mimu
 * date: 2019/10/24
 */
public class SimpleZKOperator {
    private static volatile CuratorFramework framework;
    private static final Object lock = new Object();

    static {
        if (framework == null) {
            synchronized (lock) {
                if (framework == null) {
                    framework = CuratorFrameworkFactory.builder().
                            connectString("localhost:2181").
                            connectionTimeoutMs(2000).
                            sessionTimeoutMs(10000).
                            retryPolicy(new ExponentialBackoffRetry(1000, 1)).
                            namespace("customer").
                            build();
                    framework.start();
                }
            }
        }
    }

    public CuratorFramework getFramework() {
        return framework;
    }


}
