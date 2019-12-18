package com.mimu.simple.springboot.mybatis.multipledb.aspects;

import com.mimu.simple.springboot.mybatis.multipledb.utils.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 author: mimu
 date: 2019/12/18
 */
@Aspect
public class CustomDataSourceAspect {

    @Pointcut(value = "!@annotation(com.mimu.simple.springboot.mybatis.multipledb.annotations.CustomMaster)")
    public void slavePointcut() {
    }

    @Before(value = "slavePointcut()")
    public void readDB() {
        DataSourceContextHolder.slave();
    }

    @Pointcut(value = "@annotation(com.mimu.simple.springboot.mybatis.multipledb.annotations.CustomMaster)")
    public void masterPointcut() {
    }

    @Before(value = "masterPointcut()")
    public void masterDB() {
        DataSourceContextHolder.master();
    }

}
