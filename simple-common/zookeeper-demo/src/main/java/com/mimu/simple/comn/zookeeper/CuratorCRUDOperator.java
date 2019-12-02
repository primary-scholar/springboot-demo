package com.mimu.simple.comn.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

/**
 author: mimu
 date: 2019/12/2
 */
@Slf4j
public class CuratorCRUDOperator {
    private static volatile CuratorFramework curatorClient;
    private static final Object dcl = new Object();

    static {
        if (curatorClient == null) {
            synchronized (dcl) {
                if (curatorClient == null) {
                    curatorClient = CuratorFrameworkFactory.builder().
                            connectString("localhost:2181").
                            connectionTimeoutMs(2000).
                            sessionTimeoutMs(5000).
                            retryPolicy(new ExponentialBackoffRetry(1000, 1)).
                            namespace("customer").
                            build();
                    curatorClient.start();
                }
            }
        }
    }

    public static boolean createPNode(String path, String data) throws Exception {
        try {
            curatorClient.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(path, data.getBytes());
            return true;
        } catch (Exception e) {
            log.error("createPNode error", e);
            return false;
        }
    }

    public static String readPNode(String path) throws Exception {
        try {
            byte[] bytes = curatorClient.getData().forPath(path);
            return new String(bytes);
        } catch (Exception e) {
            log.error("readPNode error", e);
            return null;
        }
    }

    public static boolean deletePNode(String path) throws Exception {
        try {
            curatorClient.delete().forPath(path);
            return true;
        } catch (Exception e) {
            log.error("deletePNode error", e);
            return false;
        }
    }

    public static CuratorFramework getClient() {
        return curatorClient;
    }

}
