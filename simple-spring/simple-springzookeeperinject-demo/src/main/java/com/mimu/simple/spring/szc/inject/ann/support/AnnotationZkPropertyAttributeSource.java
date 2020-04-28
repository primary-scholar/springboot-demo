package com.mimu.simple.spring.szc.inject.ann.support;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 author: mimu
 date: 2020/4/28
 */
public class AnnotationZkPropertyAttributeSource  implements ZKPropertyAttributeSource,Serializable {
    @Override
    public ZKPropertyAttribute getProperAttribute(Method method, Class<?> clazz) {
        return null;
    }
}
