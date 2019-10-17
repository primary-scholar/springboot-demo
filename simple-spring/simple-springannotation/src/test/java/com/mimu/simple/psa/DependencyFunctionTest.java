package com.mimu.simple.psa;

import com.mimu.simple.psa.dependency.DependencyConfig;
import com.mimu.simple.psa.dependency.ObjectA;
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
