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

    /**
     * here if we move the com.mimu.simple.springboot.mybatis.multipledb.config.AspectConfig to
     * com.mimu.simple.springboot.mybatis.multipledb.service.AspectConfig and change the method
     * customDataSourceAspect() to (select)(list)(get)(find)*customDataSourceAspect() in AspectConfig
     * the CustomDataSourceAspect advisor won't be created correctly because of circular reference.
     * why in this situation exits circular reference
     * because
     * the Class CustomDataSourceAspect is annotated with @Aspect,thus the spring container will generate custom advisor
     * by AnnotationAwareAspectJAutoProxyCreator while during the instantiation of this advisor the spring realise that
     * it should generate the proxy of CustomDataSourceAspect(wrapIfNecessary()) because of
     * the @Pointcut() of appSlavePointcut() so spring will generate CustomDataSourceAspect again
     * so this is the question!!!
     */
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
