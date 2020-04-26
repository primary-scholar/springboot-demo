package com.mimu.simple.spring.szc.inject.test.ann;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 author: mimu
 date: 2020/4/25
 */
public class ZKCenterInjectTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        context.refresh();
        ZKPropertyModel bean = context.getBean(ZKPropertyModel.class);

        System.out.println(bean.getAge());
        System.out.println(bean.getNum());
    }
}
