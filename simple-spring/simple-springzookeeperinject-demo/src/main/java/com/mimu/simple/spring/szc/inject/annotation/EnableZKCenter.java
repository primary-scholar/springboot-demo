package com.mimu.simple.spring.szc.inject.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 author: mimu
 date: 2020/4/25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AnnotationZKCenterReferenceBeanPostProcessor.class)
public @interface EnableZKCenter {
}
