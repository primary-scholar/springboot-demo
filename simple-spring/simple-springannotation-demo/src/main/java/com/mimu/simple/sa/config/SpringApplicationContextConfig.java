package com.mimu.simple.sa.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2019/7/28
 */
@Configuration
public class SpringApplicationContextConfig implements ApplicationContextAware {
    private static ApplicationContext applicationContext ;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getConfigApplicationContext() {
        return applicationContext;
    }
}
