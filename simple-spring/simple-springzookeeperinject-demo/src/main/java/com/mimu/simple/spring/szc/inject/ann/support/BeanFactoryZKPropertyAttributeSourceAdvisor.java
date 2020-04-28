package com.mimu.simple.spring.szc.inject.ann.support;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 author: mimu
 date: 2020/4/28
 */
public class BeanFactoryZKPropertyAttributeSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private ZKPropertyAttributeSource zkPropertyAttributeSource;

    @Override
    public Pointcut getPointcut() {
        return null;
    }

    public void setZkPropertyAttributeSource(ZKPropertyAttributeSource zkPropertyAttributeSource) {
        this.zkPropertyAttributeSource = zkPropertyAttributeSource;
    }


}
