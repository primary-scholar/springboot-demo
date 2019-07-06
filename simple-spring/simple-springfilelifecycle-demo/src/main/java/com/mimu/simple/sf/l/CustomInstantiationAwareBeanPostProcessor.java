package com.mimu.simple.sf.l;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * author: mimu
 * date: 2019/1/14
 */
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
