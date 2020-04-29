package com.mimu.simple.spring.szc.inject.ann.support;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 author: mimu
 date: 2020/4/28
 */
public class BeanFactoryZKPropertyAttributeSourceAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private ZKPropertyAttributeSource zkPropertyAttributeSource;

    private final ZKPropertySourcePointcut pointcut = new ZKPropertySourcePointcut() {
        @Override
        protected ZKPropertyAttributeSource getProtertyAttributeSource() {
            return zkPropertyAttributeSource;
        }
    };

    public void setZkPropertyAttributeSource(ZKPropertyAttributeSource zkPropertyAttributeSource) {
        this.zkPropertyAttributeSource = zkPropertyAttributeSource;
    }

    public void setClassFilter(ClassFilter classFilter) {
        this.pointcut.setClassFilter(classFilter);
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }


}
