package com.mimu.simple.spring.szc.inject.ann.support;

import java.lang.reflect.Method;

/**
 author: mimu
 date: 2020/4/28
 */
public interface ZKPropertyArrtibuteSource {

    ZKPropertyArrtibute getPropertyArrtibute(Method method, Class<?> clazz);
}
