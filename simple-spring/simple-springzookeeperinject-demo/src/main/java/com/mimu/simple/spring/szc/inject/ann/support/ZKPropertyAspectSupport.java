package com.mimu.simple.spring.szc.inject.ann.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;

/**
 author: mimu
 date: 2020/4/28
 */
public class ZKPropertyAspectSupport implements BeanFactoryAware,InitializingBean {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
