package com.mimu.simple.sd.consumer;

import com.mimu.simple.sd.consumer.config.ServiceConsumerConfig;
import com.mimu.simple.sd.consumer.service.CommonService;
import com.mimu.simple.sd.consumer.service.HelloService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: mimu
 * date: 2019/9/18
 */
public class SpringDubboApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ServiceConsumerConfig.class);
        configApplicationContext.start();
        System.out.println(configApplicationContext.getBean(CommonService.class).getUserData(2));

        System.out.println(configApplicationContext.getBean(HelloService.class).getHello("哈哈"));
    }
}
