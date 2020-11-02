package com.mimu.simple.spring.annotation.autowires;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SimpleAutowireTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.mimu.simple.spring.annotation.autowires");
        context.refresh();
        ComponentA bean = context.getBean(ComponentA.class);
        bean.dependencyInfo();
    }
}
