package com.mimu.simple.springboot.mybatis.multipledb.aspects;

import com.mimu.simple.springboot.mybatis.multipledb.utils.DataSourceContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 author: mimu
 date: 2019/12/18
 */
@Aspect
public class CustomDataSourceAspect {

    @Pointcut(value = "!@annotation(com.mimu.simple.springboot.mybatis.multipledb.annotations.CustomMaster)" +
            "&& (execution(* com.mimu.simple.springboot.mybatis.multipledb.service..*.select*(..))" +
            "|| execution(* com.mimu.simple.springboot.mybatis.multipledb.service..*.list*(..))" +
            "|| execution(* com.mimu.simple.springboot.mybatis.multipledb.service..*.get*(..))" +
            "|| execution(* com.mimu.simple.springboot.mybatis.multipledb.service..*.find*(..)))")
    public void appSlavePointcut() {
    }

    @Before(value = "appSlavePointcut()")
    public void appSlaveDB() {
        DataSourceContextHolder.slave();
    }

    @Pointcut(value = "@annotation(com.mimu.simple.springboot.mybatis.multipledb.annotations.CustomMaster)" +
            "|| execution(* com.mimu.simple.springboot.mybatis.multipledb.service..*.insert*(..))" +
            "|| execution(* com.mimu.simple.springboot.mybatis.multipledb.service..*.add*(..))" +
            "|| execution(* com.mimu.simple.springboot.mybatis.multipledb.service..*.update*(..))" +
            "|| execution(* com.mimu.simple.springboot.mybatis.multipledb.service..*.delete*(..))" +
            "|| execution(* com.mimu.simple.springboot.mybatis.multipledb.service..*.remove*(..))")
    public void appMasterPointcut() {
    }

    @Before(value = "appMasterPointcut()")
    public void appMasterDB() {
        DataSourceContextHolder.master();
    }

}
