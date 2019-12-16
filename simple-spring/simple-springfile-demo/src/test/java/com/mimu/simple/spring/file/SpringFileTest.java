package com.mimu.simple.spring.file;

import com.mimu.simple.spring.file.controller.inner.InnerController;
import com.mimu.simple.spring.file.model.*;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFileTest {

    @Test
    public void info(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
        InnerController controller = context.getBean("innerController", InnerController.class);
        controller.message();
    }

    @Test
    public void info1(){
        ObjectA objectA = new ObjectA();
        objectA.setaName("aName");
        objectA.setDesc("a desc");
        ObjectAFieldFirst objectAFieldFirst = new ObjectAFieldFirst();
        objectAFieldFirst.setOaFieldFirst(1);
        ObjectAFieldSecondExtendsObjectBFieldSecond objectAFieldSecondExtendsObjectBFieldSecond = new ObjectAFieldSecondExtendsObjectBFieldSecond();
        objectAFieldSecondExtendsObjectBFieldSecond.setOaFieldSecond(2);
        objectA.setFieldFirst(objectAFieldFirst);
        objectA.setFieldSecond(objectAFieldSecondExtendsObjectBFieldSecond);
        ObjectA.InnerClass oaInner = new ObjectA.InnerClass();
        oaInner.setId(10);

        ObjectB objectB = new ObjectB();

        BeanUtils.copyProperties(objectA, objectB);
        System.out.println(objectB);
    }

    @Test
    public void info2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
        SourceModelA bean = context.getBean(SourceModelA.class);
        System.out.println(bean);
    }
}
