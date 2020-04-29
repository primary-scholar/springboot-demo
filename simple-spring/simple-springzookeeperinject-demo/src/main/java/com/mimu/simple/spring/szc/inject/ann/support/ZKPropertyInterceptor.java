package com.mimu.simple.spring.szc.inject.ann.support;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;

import java.io.Serializable;

/**
 author: mimu
 date: 2020/4/28
 */
public class ZKPropertyInterceptor extends ZKPropertyAspectSupport implements MethodInterceptor, Serializable {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
        return invokeWithIntercept(invocation.getMethod(), targetClass);
    }


}
