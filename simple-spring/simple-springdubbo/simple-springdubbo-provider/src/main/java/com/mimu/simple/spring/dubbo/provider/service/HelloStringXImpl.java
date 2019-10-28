package com.mimu.simple.spring.dubbo.provider.service;

import com.mimu.simple.spring.dubbo.api.HelloStringApi;
import com.mimu.simple.sd.core.model.HelloData;
import com.mimu.simple.spring.dubbo.model.HelloDataX;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/9/27
 */
@Slf4j
@Service(interfaceClass = HelloStringApi.class)
@Component
public class HelloStringXImpl implements HelloStringApi {
    @Override
    public HelloData hello(String name) {
        HelloDataX build = new HelloDataX(name, 1);
        log.info("datax={}", build);
        return build;
    }
}
