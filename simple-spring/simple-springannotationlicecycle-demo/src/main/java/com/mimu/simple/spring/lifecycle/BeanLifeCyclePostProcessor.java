package com.mimu.simple.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;

@Component
public class BeanLifeCyclePostProcessor implements MergedBeanDefinitionPostProcessor, SmartInstantiationAwareBeanPostProcessor {
    private static final String defaultBeanName = "applicationConfig";

    @Override
    public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        if (defaultBeanName.equals(beanName))
            System.out.println("postProcessMergedBeanDefinition invoke...");
    }

    @Override
    public void resetBeanDefinition(String beanName) {
        if (defaultBeanName.equals(beanName))
            System.out.println("resetBeanDefinition invoke...");
    }

    @Override
    public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
        if (defaultBeanName.equals(beanName))
            System.out.println("predictBeanType invoke...");
        return SmartInstantiationAwareBeanPostProcessor.super.predictBeanType(beanClass, beanName);
    }

    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        if (defaultBeanName.equals(beanName))
            System.out.println("determineCandidateConstructors invoke...");
        return SmartInstantiationAwareBeanPostProcessor.super.determineCandidateConstructors(beanClass, beanName);
    }

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        if (defaultBeanName.equals(beanName))
            System.out.println("getEarlyBeanReference invoke...");
        return SmartInstantiationAwareBeanPostProcessor.super.getEarlyBeanReference(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (defaultBeanName.equals(beanName))
            System.out.println("postProcessBeforeInstantiation invoke...");
        return SmartInstantiationAwareBeanPostProcessor.super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (defaultBeanName.equals(beanName))
            System.out.println("postProcessAfterInstantiation invoke...");
        return SmartInstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (defaultBeanName.equals(beanName))
            System.out.println("postProcessProperties invoke...");
        return SmartInstantiationAwareBeanPostProcessor.super.postProcessProperties(pvs, bean, beanName);
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if (defaultBeanName.equals(beanName))
            System.out.println("postProcessPropertyValues invoke...");
        return SmartInstantiationAwareBeanPostProcessor.super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (defaultBeanName.equals(beanName))
            System.out.println("postProcessBeforeInitialization invoke...");
        return MergedBeanDefinitionPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (defaultBeanName.equals(beanName))
            System.out.println("postProcessAfterInitialization invoke...");
        return MergedBeanDefinitionPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
