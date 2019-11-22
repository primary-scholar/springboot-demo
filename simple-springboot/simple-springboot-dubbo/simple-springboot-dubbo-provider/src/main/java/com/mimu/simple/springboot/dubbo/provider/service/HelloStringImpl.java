package com.mimu.simple.springboot.dubbo.provider.service;

import com.mimu.simple.springboot.dubbo.api.HelloStringApi;
import com.mimu.simple.springboot.dubbo.model.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/9/18
 */
@Slf4j
@Service(interfaceClass = HelloStringApi.class, timeout = 100)
@Component
public class HelloStringImpl implements HelloStringApi {
    @Override
    public HelloData hello(String name) throws Exception {
        HelloData build = new HelloData(name);
        log.info("data={}", build);
        return build;
    }
}
