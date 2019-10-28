package com.mimu.simple.spring.file.lifecycle.l;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * author: mimu
 * date: 2019/1/14
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public CustomBeanFactoryPostProcessor() {
        System.out.println("CustomBeanFactoryPostProcessor constructor() invoke...");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("CustomBeanFactoryPostProcessor postProcessBeanFactory() invoke...");
        BeanDefinition definition = beanFactory.getBeanDefinition("person");
        definition.getPropertyValues().addPropertyValue("phone",123);
    }
}
