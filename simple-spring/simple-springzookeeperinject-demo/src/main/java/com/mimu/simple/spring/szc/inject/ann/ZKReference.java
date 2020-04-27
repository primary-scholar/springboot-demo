package com.mimu.simple.spring.szc.inject.ann;

import java.lang.annotation.*;

/**
 author: mimu
 date: 2020/4/25
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZKReference {

    String key() default "";

    String value() default "";
}
