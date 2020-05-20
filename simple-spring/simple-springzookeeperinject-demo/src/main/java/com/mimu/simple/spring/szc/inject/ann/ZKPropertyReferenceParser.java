package com.mimu.simple.spring.szc.inject.ann;

import com.mimu.simple.spring.szc.inject.ann.interceptor.ZKPropertyAttribute;

import java.lang.reflect.AnnotatedElement;

/**
 author: mimu
 date: 2020/4/29
 */
public interface ZKPropertyReferenceParser {

    ZKPropertyAttribute parseZKReferenceAnnotation(AnnotatedElement element);
}
