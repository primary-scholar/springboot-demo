package com.mimu.simple.spring.file.lifecycle;


import com.mimu.simple.spring.file.lifecycle.d.DomainC;
import com.mimu.simple.spring.file.lifecycle.l.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringCycleDependenceTest {

    @Test
    public void info() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factorybean.xml");
        System.out.println(applicationContext.getBean("&personFactroyBean"));
        System.out.println(applicationContext.getBean("personFactroyBean"));
    }

    @Test
    public void info1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanlifecycle.xml");
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person.getName());
    }

    /**
     * 对于 非 singleton 的 实例解析，spring 无法解决循环依赖的问题
     */
    @Test
    public void info2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanlifecycle.xml");
        DomainC bean = applicationContext.getBean(DomainC.class);
        System.out.println(bean);
    }

}
