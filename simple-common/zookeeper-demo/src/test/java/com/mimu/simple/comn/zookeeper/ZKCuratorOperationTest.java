package com.mimu.simple.comn.zookeeper;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

/**
 author: mimu
 date: 2019/12/11
 */
public class ZKCuratorOperationTest {
    private String path = "/abc",data ="bac";

    @Test
    public void info() throws Exception {
        CuratorFramework client
                = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(1000,1));
        client.start();
        System.out.println(ZKCuratorOperation.createPersistentNode(path, data));
        ZKCuratorOperation.addNodeListener(client,path,false);
        ZKCuratorOperation.addPathChildListener(client,path,false);
        ZKCuratorOperation.addTreeListener(client,path,false);
        System.out.println(ZKCuratorOperation.readNode(path));
        ZKCuratorOperation.deleteNode(path);
    }
}