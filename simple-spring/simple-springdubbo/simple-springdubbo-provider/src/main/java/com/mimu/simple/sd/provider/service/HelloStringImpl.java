package com.mimu.simple.sd.provider.service;

import com.mimu.simple.sd.api.HelloStringApi;
import com.mimu.simple.sd.core.model.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/9/18
 */

/**
 * 如果使用 spring-dubbo-provider-annotation.xml 文件则需 在service 类上添加
 * dubbo @Service 注解
 */
@Slf4j
@Service(interfaceClass = HelloStringApi.class, timeout = 10)
@Component
public class HelloStringImpl implements HelloStringApi {
    @Override
    public HelloData hello(String name) {
        HelloData build = HelloData.builder().name(name).build();
        log.info("data={}", build);
        return build;
    }
}
