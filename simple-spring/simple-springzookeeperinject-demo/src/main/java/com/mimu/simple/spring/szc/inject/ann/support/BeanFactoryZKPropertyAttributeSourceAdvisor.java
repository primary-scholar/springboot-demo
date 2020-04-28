package com.mimu.simple.spring.szc.inject.ann.support;

import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 author: mimu
 date: 2020/4/28
 */
public class BeanFactoryZKPropertyAttributeSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private ZKPropertyArrtibuteSource zkPropertyArrtibuteSource;

    @Override
    public Pointcut getPointcut() {
        return null;
    }

    public void setZkPropertyArrtibuteSource(ZKPropertyArrtibuteSource zkPropertyArrtibuteSource) {
        this.zkPropertyArrtibuteSource = zkPropertyArrtibuteSource;
    }


}
