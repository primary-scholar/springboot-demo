package com.mimu.simple.sd.consumer.service;

import com.mimu.simple.sd.api.HelloStringApi;
import com.mimu.simple.sd.core.model.HelloData;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;


/**
 * author: mimu
 * date: 2019/9/18
 */

@Service
public class HelloService {

    @Reference(check = false, retries = 0,version = "*")
    /**
     * 可以使用直联方式 进行服务的消费，一般用于测试环境测试接口的可用性，直联方式不依赖zk的参与
     */
    //@Reference(check = false, retries = 0, url = "dubbo://10.2.132.47:20880?version=1.0.0")
    private HelloStringApi helloStringApi;

    public HelloData getHello(String name) {
        return helloStringApi.hello(name);
    }
}
