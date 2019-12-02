package com.mimu.simple.comn.zookeeper;

import org.junit.Test;


/**
 * author: mimu
 * date: 2019/10/27
 */
public class SimpleZKOperatorTest {
    private static final String path = "/abc", data = "abc";

    @Test
    public void info() throws Exception {
        System.out.println(SimpleZKOperator.CuratorCRUDOperator.createPNode(path, data));
        System.out.println(SimpleZKOperator.CuratorCRUDOperator.readPNode(path));
        System.out.println(SimpleZKOperator.CuratorCRUDOperator.deletePNode(path));
    }

    @Test
    public void dl(){

    }
}