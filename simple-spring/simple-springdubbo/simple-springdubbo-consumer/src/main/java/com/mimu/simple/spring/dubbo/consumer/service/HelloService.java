package com.mimu.simple.spring.dubbo.consumer.service;

import com.mimu.simple.spring.dubbo.api.HelloStringApi;
import com.mimu.simple.sd.core.model.HelloData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * author: mimu
 * date: 2019/9/18
 */

/**
 * 使用 spring-dubbo-consumer.xml 文件则需要 注释掉 ServiceConsumerConfig 类， 在该类上添加 dubbo @Service 注解 并注释掉 @Reference 注解
 * 同时 在 属性上 添加 @Autowire 注解
 */

@Component
@Service
public class HelloService {

    @Autowired
    private HelloStringApi helloStringApi;

    public HelloData getHello(String name) {
        return helloStringApi.hello(name);
    }
}
