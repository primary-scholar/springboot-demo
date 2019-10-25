package com.mimu.simple.szk;


import org.junit.Test;

/**
 * author: mimu
 * date: 2019/10/25
 */
public class SimpleZKOperatorTest {
    private static final String path = "/abc",data="abc";

    @Test
    public void info() throws Exception {
        System.out.println(SimpleZKOperator.curatorCRUDOperator.createPNode(path,data));
        System.out.println(SimpleZKOperator.curatorCRUDOperator.readPNode(path));
        System.out.println(SimpleZKOperator.curatorCRUDOperator.deletePNode(path));
    }
}
