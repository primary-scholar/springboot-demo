package com.mimu.simple.sd.provider.service;

import com.mimu.simple.sd.api.HelloStringApi;
import com.mimu.simple.sd.core.model.HelloData;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * author: mimu
 * date: 2019/9/18
 */
@Service(interfaceClass = HelloStringApi.class, timeout = 5000)
@Component
public class HelloStringImpl implements HelloStringApi {
    @Override
    public HelloData hello(String name) {
        return HelloData.builder().name(name).build();
    }
}
