package com.mimu.simple.spring.szc.inject.test.annotation.app;

import com.mimu.simple.spring.szc.inject.anno.app.ApplicationConfig;
import com.mimu.simple.spring.szc.inject.anno.app.ZKPropertyModel;
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
        while (true){
            System.out.println(bean.getAge());
            System.out.println(bean.getNum());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
