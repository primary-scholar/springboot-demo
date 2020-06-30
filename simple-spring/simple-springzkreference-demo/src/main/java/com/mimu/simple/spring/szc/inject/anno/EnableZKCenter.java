package com.mimu.simple.spring.szc.inject.anno;

import com.mimu.simple.spring.szc.inject.anno.support.AnnotationZKPropertyReferenceBeanPostProcessor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 author: mimu
 date: 2020/4/25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(AnnotationZKPropertyReferenceBeanPostProcessor.class)
public @interface EnableZKCenter {
}
