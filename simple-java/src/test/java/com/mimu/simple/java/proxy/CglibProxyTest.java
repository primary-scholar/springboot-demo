package com.mimu.simple.java.proxy;

import org.junit.Test;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class CglibProxyTest {

    @Test
    public void printInfo() {
        Subject subject = new CglibProxy().getProxy(Subject.class);
        subject.printInfo();
        subject.printInfoAgain();
    }
}
