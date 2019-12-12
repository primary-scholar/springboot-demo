package com.mimu.simple.comn.zookeeper;


import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;

import java.util.List;

/**
 author: mimu
 date: 2019/12/11
 */
public class ZKCuratorOperationTest {
    private String path = "/abc", data = "bac";

    @Test
    public void info() throws Exception {
        CuratorFramework client = ZKCuratorOperation.getCuratorClient();
        System.out.println(ZKCuratorOperation.createPersistentNode(path, data));
        ZKCuratorOperation.addNodeListener(client, path, false);
        ZKCuratorOperation.addPathChildListener(client, path, false);
        ZKCuratorOperation.addTreeListener(client, path, false);
        System.out.println(ZKCuratorOperation.readNode(path));
        ZKCuratorOperation.setData(path, "abc");
        System.out.println(ZKCuratorOperation.readNode(path));
        List<String> children = ZKCuratorOperation.getChildren(path);
        for (String item : children) {
            System.out.println(item);
        }
        ZKCuratorOperation.deleteNode(path);
    }
}