package com.mimu.simple.sa.l;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 不添加以下两个注解也ok
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class SpringAnnotationLifeCycleTest {

    @Test
    public void info(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.scan("com.mimu.simple.sa.l");
        annotationConfigApplicationContext.refresh();
        Person person = annotationConfigApplicationContext.getBean("person",Person.class);
        System.out.println(person.getName());
    }
}
