package com.mimu.simple.comn.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.concurrent.TimeUnit;


/**
 * author: mimu
 * date: 2019/10/27
 */
@Slf4j
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
        String path = "/curator/locks";
        try {
            CuratorFramework client
                    = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(1000,1));
            client.start();
            CuratorDLOperator curatorDLOperator = new CuratorDLOperator(client, path);
            log.info("getLock: {}", curatorDLOperator.getLock(5, TimeUnit.SECONDS));
        } catch (Exception e) {
            log.error("getLock error {}", e);
        }

        try {
            CuratorFramework client
                    = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(1000,3));
            client.start();
            CuratorDLOperator curatorDLOperator = new CuratorDLOperator(client, path);
            log.info("getLock: {}", curatorDLOperator.getLock(5, TimeUnit.SECONDS));
            log.info("releaseLock {}",curatorDLOperator.releaseLock());
        } catch (Exception e) {
            log.error("getLock error {}", e);
        }
    }
}