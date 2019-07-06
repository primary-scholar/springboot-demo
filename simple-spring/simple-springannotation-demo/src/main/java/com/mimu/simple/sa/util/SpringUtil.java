package com.mimu.simple.sa.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2019/6/30
 */
@Configuration
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext ;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getConfigApplicationContext() {
        return applicationContext;
    }
}
