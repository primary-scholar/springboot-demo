package com.mimu.simple.spring.lifecycle;


import com.mimu.simple.spring.lifecycle.ApplicationConfig;
import com.mimu.simple.spring.lifecycle.Person;
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
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        Person person = annotationConfigApplicationContext.getBean("person",Person.class);
        System.out.println(person.getName());
    }
}
