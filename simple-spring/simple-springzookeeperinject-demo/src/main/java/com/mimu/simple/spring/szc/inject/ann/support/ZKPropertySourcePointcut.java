package com.mimu.simple.spring.szc.inject.ann.support;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 author: mimu
 date: 2020/4/28
 */
public abstract class ZKPropertySourcePointcut extends StaticMethodMatcherPointcut implements Serializable {
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        ZKPropertyAttributeSource source = getProtertyAttributeSource();
        return (source == null || source.getPropertyArrtibute(method, targetClass) != null);
    }

    protected abstract ZKPropertyAttributeSource getProtertyAttributeSource();
}
