package com.mimu.simple.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/1/14
 */
@Component
public class CustomInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("CustomInstantiationAwareBeanPostProcessor postProcessProperties() invoke...");
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomInstantiationAwareBeanPostProcessor postProcessBeforeInitialization() invoke...");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomInstantiationAwareBeanPostProcessor postProcessAfterInitialization() invoke...");
        return bean;
    }
}
