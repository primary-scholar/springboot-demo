package com.mimu.simple.spring.annotation.demo.service;

import com.mimu.simple.spring.annotation.demo.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 author: mimu
 date: 2020/3/19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class TermTransactionServiceATest {

    @Autowired
    private TermTransactionServiceA termTransactionServiceA;

    @Test
    public void info1() {
        termTransactionServiceA.info1();
    }

    @Test
    public void info2() {
        termTransactionServiceA.info2();
    }

    @Test
    public void info3() {
        termTransactionServiceA.info3();
    }
}