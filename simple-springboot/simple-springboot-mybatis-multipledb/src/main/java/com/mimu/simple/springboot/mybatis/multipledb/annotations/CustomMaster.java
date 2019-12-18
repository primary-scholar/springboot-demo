package com.mimu.simple.springboot.mybatis.multipledb.annotations;

/**
 * author: mimu
 * date: 2019/12/18
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * here we use @CustomMaster annotation to indicate that the method which is annotated by @CustomMaster
 * will read or write the record form master dataSource
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomMaster {
}
