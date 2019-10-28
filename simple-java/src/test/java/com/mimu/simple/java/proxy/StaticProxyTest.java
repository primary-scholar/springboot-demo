package com.mimu.simple.java.proxy;

import org.junit.Test;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class StaticProxyTest {

    @Test
    public void printInfoTest() {
        SubjectInterface subjectInterface = new StaticProxy(new Subject());
        subjectInterface.printInfo();
        subjectInterface.printInfoAgain();
    }
}
