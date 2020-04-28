package com.mimu.simple.spring.szc.inject.ann.support;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 author: mimu
 date: 2020/4/28
 */
public class ZKPropertyInterceptor extends ZKPropertyAspectSupport implements MethodInterceptor,Serializable {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        return null;
    }
}
