package com.mimu.simple.spring.dubbo.provider;

import com.mimu.simple.spring.dubbo.provider.config.ApplicationServerConfig;
import com.mimu.simple.spring.dubbo.provider.service.UserDataApiImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: mimu
 * date: 2019/9/17
 */
public class ApplicationContextTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationServerConfig.class);
        context.refresh();
        System.out.println(context.getBean(UserDataApiImpl.class));

    }
}
