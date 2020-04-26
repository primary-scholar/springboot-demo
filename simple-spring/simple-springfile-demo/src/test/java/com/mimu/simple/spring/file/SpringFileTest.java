package com.mimu.simple.spring.file;

import com.mimu.simple.spring.file.controller.inner.InnerController;
import com.mimu.simple.spring.file.model.*;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFileTest {

    @Test
    public void info() {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
        InnerController controller = context.getBean("innerController", InnerController.class);
        controller.message();
    }

    /**
     * beanUtils.copy()
     * 1.首先获取 target 中的所有 PropertyDescriptor[] descriptor
     * 2.遍历descriptor 获取 write descriptor
     * 3.根据 write descriptor 获取 source 中的 PropertyDescriptor 的read method
     * 4.如果 method 的 write method 和 read method 的 parameterType 一致(java 中的参数类型一致)则进行 赋值 method.invoke()
     */
    @Test
    public void info1() {
        ObjectA objectA = new ObjectA();
        objectA.setId(1);
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
    public void info2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
        SourceModelA bean = context.getBean(SourceModelA.class);
        System.out.println(bean);
    }
}
