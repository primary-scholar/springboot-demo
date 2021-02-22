package com.mimu.simple.spring.annotation.demo.service;

import com.mimu.simple.spring.annotation.demo.model.PersonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 使用 @Async 标注的 方法 或 类 spring 容器 会使用 AsyncAnnotationBeanPostProcessor 的  beanPostProcessor
 * 进行 处理 为该类 生成一个 代理对象 以便使 被 @Async 标注的方法在 异步线程池 中 异步执行
 * 而 ProxyAsyncConfiguration 主要用于 生成 AsyncAnnotationBeanPostProcessor 对象 并对 该对象进行 定制化处理
 *
 */

@Service
public class AsyncCommonService {
    private static Logger logger = LoggerFactory.getLogger(AsyncCommonService.class);

    @Async
    public PersonData getPersonDataById(int id) {
        PersonData async = PersonData.builder().nickName("async").pid(id).build();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("info {}", async);
        return async;
    }

    @Async
    public PersonData getPersonDataByName(String name) {
        PersonData async = PersonData.builder().nickName(name).pid(111).build();
        try {
            Thread.sleep(2010);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("info {}", async);
        return async;
    }
}
