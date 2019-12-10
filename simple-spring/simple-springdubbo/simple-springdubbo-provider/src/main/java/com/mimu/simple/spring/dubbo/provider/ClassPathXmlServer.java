package com.mimu.simple.spring.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 author: mimu
 date: 2019/12/10
 */
public class ClassPathXmlServer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
        context.start();
    }
}
