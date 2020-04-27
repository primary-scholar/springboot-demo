package com.mimu.simple.spring.szc.inject.ann;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 author: mimu
 date: 2020/4/27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableZKCenter {
    @AliasFor("basePackages")
    String[] value() default {};

    @AliasFor("value")
    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

}
