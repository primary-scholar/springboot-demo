package com.mimu.simple.springboot.zkr.annotation;

import com.mimu.simple.springboot.zkr.annotation.interceptor.ZKPropertyAttribute;

import java.lang.reflect.AnnotatedElement;

/**
 author: mimu
 date: 2020/4/29
 */
public interface ZKPropertyReferenceParser {

    ZKPropertyAttribute parseZKReferenceAnnotation(AnnotatedElement element);
}
