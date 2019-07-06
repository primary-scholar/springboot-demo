package com.mimu.simple.sj.proxy;

import org.junit.Test;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class DynamicProxyTest {

    @Test
    public void printInfo() {
        SubjectInterface subjectInterface = new DynamicProxy(new Subject()).getProxyObject();
        subjectInterface.printInfo();
        subjectInterface.printInfoAgain();
    }
}
