package com.mimu.simple.sf;


import com.mimu.simple.sf.fb.ModelAFactoryBean;
import com.mimu.simple.sf.l.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCycleDependenceTest {

    @Test
    public void info(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factorybean.xml");
        System.out.println(applicationContext.getBean("personFactroyBean"));
    }

    @Test
    public void info1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanlifecycle.xml");
        Person person = applicationContext.getBean("person",Person.class);
        System.out.println(person.getName());
    }
}
