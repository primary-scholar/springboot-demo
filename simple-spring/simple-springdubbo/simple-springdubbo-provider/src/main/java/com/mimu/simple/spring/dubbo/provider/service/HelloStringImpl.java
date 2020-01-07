package com.mimu.simple.spring.dubbo.provider.service;

import com.mimu.simple.spring.dubbo.api.HelloStringApi;
import com.mimu.simple.spring.dubbo.exceptions.CustomException;
import com.mimu.simple.spring.dubbo.model.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/9/18
 */

/**
 * 如果使用 spring-dubbo-provider.xml 文件则需 在service 类上添加
 * dubbo @Service 注解
 */
@Slf4j
//@Service(interfaceClass = HelloStringApi.class, timeout = 100)
@Component
public class HelloStringImpl implements HelloStringApi {
    @Override
    public HelloData hello(String name) throws Exception {
        if (Math.random() > 0.5) {
            throw new CustomException("custom");
        }
        HelloData build = new HelloData(name);
        log.info("data={}", build);
        return build;
    }

    @Override
    public String hello2(String name) {
        return "hello2 method " + name;
    }
}
