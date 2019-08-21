package com.mimu.simple.psa;

import com.mimu.simple.psa.autowires.ComponentA;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SimpleAutowireTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.mimu.simple.psa.autowires");
        context.refresh();
        ComponentA bean = context.getBean(ComponentA.class);
        bean.dependencyInfo();
    }
}
