package com.mimu.simple.spring.dubbo.provider.service;

import com.mimu.simple.spring.dubbo.api.HelloStringApi;
import com.mimu.simple.spring.dubbo.model.HelloData;
import com.mimu.simple.spring.dubbo.model.HelloDataX;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/9/27
 */

/**
 * 如果使用 spring-dubbo-provider-annotation.xml 文件则需 在service 类上添加
 * dubbo @Service 注解
 */
@Slf4j
//@Service(interfaceClass = HelloStringApi.class)
@Component
public class HelloStringXImpl implements HelloStringApi {
    @Override
    public HelloData hello(String name) throws Exception {
        HelloDataX build = new HelloDataX(name, 1);
        log.info("datax={}", build);
        return build;
    }
}
