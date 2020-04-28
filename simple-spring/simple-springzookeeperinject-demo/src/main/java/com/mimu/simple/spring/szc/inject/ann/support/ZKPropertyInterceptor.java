package com.mimu.simple.spring.szc.inject.ann.support;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;

/**
 author: mimu
 date: 2020/4/28
 */
public class ZKPropertyInterceptor extends ZKPropertyAspectSupport implements MethodInterceptor, Serializable {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return null;
    }
}
