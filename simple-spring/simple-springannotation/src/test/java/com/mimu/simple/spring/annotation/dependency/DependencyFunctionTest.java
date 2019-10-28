package com.mimu.simple.spring.annotation.dependency;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: mimu
 * date: 2019/10/17
 */
public class DependencyFunctionTest {
    @Test
    public void info() {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(DependencyConfig.class);
        System.out.println(configApplicationContext.getBean(ObjectA.class));
    }
}
