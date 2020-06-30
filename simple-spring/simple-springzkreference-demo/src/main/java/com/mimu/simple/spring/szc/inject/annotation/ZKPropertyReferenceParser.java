package com.mimu.simple.spring.szc.inject.annotation;

import com.mimu.simple.spring.szc.inject.annotation.interceptor.ZKPropertyAttribute;

import java.lang.reflect.AnnotatedElement;

/**
 author: mimu
 date: 2020/4/29
 */
public interface ZKPropertyReferenceParser {

    ZKPropertyAttribute parseZKReferenceAnnotation(AnnotatedElement element);
}
