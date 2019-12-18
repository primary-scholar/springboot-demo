package com.mimu.simple.springboot.mybatis.multipledb.annotations;

/**
 author: mimu
 date: 2019/12/18
 */

/**
 * here we use @CustomMaster annotation to indicate that the method which is annotated by @CustomMaster
 * will read or write the record form master dataSource
 */
public @interface CustomMaster {
}
