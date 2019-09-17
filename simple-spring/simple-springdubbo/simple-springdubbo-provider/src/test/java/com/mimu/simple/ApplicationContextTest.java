package com.mimu.simple;

import com.mimu.simple.sd.provider.config.ApplicationConfig;
import com.mimu.simple.sd.provider.service.UserDataApiImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: mimu
 * date: 2019/9/17
 */
public class ApplicationContextTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ApplicationConfig.class);
        context.refresh();
        System.out.println(context.getBean(UserDataApiImpl.class));

    }
}
