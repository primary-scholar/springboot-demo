package com.mimu.simple.comn.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 author: mimu
 date: 2019/12/11
 */
@Slf4j
public class ZKCuratorLockTest {
    @Test
    public void dl() throws Exception {
        String path = "/curator/locks";
        try {
            CuratorFramework client
                    = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(1000,1));
            client.start();
            ZKCuratorLock ZKCuratorLock = new ZKCuratorLock(client, path);
            log.info("getLock: {}", ZKCuratorLock.getLock(5, TimeUnit.SECONDS));
        } catch (Exception e) {
            log.error("getLock error {}", e);
        }

        try {
            CuratorFramework client
                    = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(1000,3));
            client.start();
            ZKCuratorLock ZKCuratorLock = new ZKCuratorLock(client, path);
            log.info("getLock: {}", ZKCuratorLock.getLock(5, TimeUnit.SECONDS));
        } catch (Exception e) {
            log.error("getLock error {}", e);
        }
    }
}