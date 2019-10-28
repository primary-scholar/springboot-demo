package com.mimu.simple.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class DynamicProxy implements InvocationHandler {

    private Object target;

    DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxyObject() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public void before() {
        System.out.println("Before");
    }

    public void after() {
        System.out.println("After");
        System.out.println();
    }
}
