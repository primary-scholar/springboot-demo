package com.mimu.simple.sd.consumer.service;

import com.mimu.simple.sd.api.HelloStringApi;
import com.mimu.simple.sd.core.model.HelloData;
import org.apache.dubbo.config.annotation.Reference;
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

    //@Reference(check = false, retries = 0,version = "*")
    /**
     * 可以使用直联方式 进行服务的消费，一般用于测试环境测试接口的可用性，直联方式不依赖zk的参与
     */
    //@Reference(check = false, retries = 0, url = "dubbo://10.2.132.47:20880?version=1.0.0")
    @Autowired
    private HelloStringApi helloStringApi;

    public HelloData getHello(String name) {
        return helloStringApi.hello(name);
    }
}
