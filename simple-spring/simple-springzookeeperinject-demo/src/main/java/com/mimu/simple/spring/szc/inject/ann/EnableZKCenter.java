package com.mimu.simple.spring.szc.inject.ann;

import com.mimu.simple.spring.szc.inject.ann.support.ZKCenterComponentScanRegistrar;
import com.mimu.simple.spring.szc.inject.ann.support.AnnotationZKCenterReferenceBeanPostProcessor;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 author: mimu
 date: 2020/4/25
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ZKCenterComponentScanRegistrar.class,AnnotationZKCenterReferenceBeanPostProcessor.class})
public @interface EnableZKCenter {

    @AliasFor("basePackages")
    String[] value() default {};

    @AliasFor("value")
    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};
}
