package com.mimu.simple.spring.szc.inject.ann.interceptor;

import java.lang.reflect.Method;

/**
 author: mimu
 date: 2020/4/28
 */
public interface ZKPropertyAttributeSource {

    ZKPropertyAttribute getProperAttribute(Method method, Class<?> clazz);
}
