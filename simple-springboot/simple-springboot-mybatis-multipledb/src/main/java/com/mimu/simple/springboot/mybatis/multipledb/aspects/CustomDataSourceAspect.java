package com.mimu.simple.springboot.mybatis.multipledb.aspects;

import com.mimu.simple.springboot.mybatis.multipledb.utils.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 author: mimu
 date: 2019/12/18
 */
@Aspect
public class CustomDataSourceAspect {

    /**
     * here if we move the com.mimu.simple.springboot.mybatis.multipledb.service.AspectConfig to
     * com.mimu.simple.springboot.mybatis.multipledb.service.AspectConfig and change the method
     * findcustomDataSourceAspect() to (select)(list)(get)(find)*findcustomDataSourceAspect() in AspectConfig
     * the CustomDataSourceAspect advisor won't be created correctly because of circular reference.
     * why in this situation exits circular reference
     * because
     * the Class CustomDataSourceAspect is annotated with @Aspect,thus the spring container will generate custom advisor
     * by AnnotationAwareAspectJAutoProxyCreator(wrapIfNecessary()), while during the instantiation of this advisor(spring use
     * instantiateUsingFactoryMethod() method instanced the advisor,so spring get the factoryBean firstly(in our situation the
     * class of factoryBean is com.mimu.simple.springboot.mybatis.multipledb.service.AspectConfig and the class is a proxy
     * generate by spring with cglib) in the following steps spring will use proxy method to generate the advosor the steps
     * like this: proxyMethod()->appReadDB()-> factoryMethod.findCustomDataSourceAspect() to get the aspect instance so spring
     * will generate CustomDataSourceAspect again.
     * here this is the question!!!
     */
    @Pointcut(value = "!@annotation(com.mimu.simple.springboot.mybatis.multipledb.annotations.CustomMaster)" +
            "&& (execution(public * com.mimu.simple.springboot.mybatis.multipledb.service..*.select*(..))" +
            "|| execution(public * com.mimu.simple.springboot.mybatis.multipledb.service..*.list*(..))" +
            "|| execution(public * com.mimu.simple.springboot.mybatis.multipledb.service..*.get*(..))" +
            "|| execution(public * com.mimu.simple.springboot.mybatis.multipledb.service..*.find*(..)))")
    public void appSlavePointcut() {
    }

    @Around(value = "appSlavePointcut()")
    public Object appReadDB(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        DataSourceContextHolder.slave();
        Object object = proceedingJoinPoint.proceed();
        DataSourceContextHolder.remove();
        return object;
    }

    @Pointcut(value = "@annotation(com.mimu.simple.springboot.mybatis.multipledb.annotations.CustomMaster)" +
            "|| execution(public * com.mimu.simple.springboot.mybatis.multipledb.service..*.insert*(..))" +
            "|| execution(public * com.mimu.simple.springboot.mybatis.multipledb.service..*.add*(..))" +
            "|| execution(public * com.mimu.simple.springboot.mybatis.multipledb.service..*.update*(..))" +
            "|| execution(public * com.mimu.simple.springboot.mybatis.multipledb.service..*.delete*(..))" +
            "|| execution(public * com.mimu.simple.springboot.mybatis.multipledb.service..*.remove*(..))")
    public void appMasterPointcut() {
    }

    @Around(value = "appMasterPointcut()")
    public Object appWriteDB(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        DataSourceContextHolder.master();
        Object object = proceedingJoinPoint.proceed();
        DataSourceContextHolder.remove();
        return object;
    }

}
