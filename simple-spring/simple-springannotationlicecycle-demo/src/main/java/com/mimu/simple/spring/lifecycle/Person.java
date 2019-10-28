package com.mimu.simple.spring.lifecycle;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/1/14
 */
@Component
public class Person implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private String name;
    private int phone;

    private BeanFactory beanFactory;
    private String beanName;

    public Person() {
        System.out.println("Person constructor() invoke...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Person setName() invoke...");
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        System.out.println("Person setPhone() invoke...");
        this.phone = phone;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Person BeanFactoryAware setBeanFactory() invoke...");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Person BeanNameAware setBeanName() invoke...");
        this.beanName = name;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Person DisposableBean destroy() invoke...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Person InitializingBean afterPropertiesSet() invoke...");
    }

    public void customInit() {
        System.out.println("Person init-method invoke...");
    }

    public void customDestory() {
        System.out.println("Person destory-method invoke...");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                ", beanFactory=" + beanFactory +
                ", beanName='" + beanName + '\'' +
                '}';
    }
}
