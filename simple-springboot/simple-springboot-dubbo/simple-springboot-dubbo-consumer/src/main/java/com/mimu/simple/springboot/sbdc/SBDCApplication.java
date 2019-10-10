package com.mimu.simple.springboot.sbdc;

import com.mimu.simple.springboot.sbdc.config.ServiceConsumerConfig;
import com.mimu.simple.springboot.sbdc.service.CommonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: mimu
 * date: 2019/8/18
 */
public class SBDCApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.register(ServiceConsumerConfig.class);
        configApplicationContext.refresh();
        System.out.println(configApplicationContext.getBean(CommonService.class).getUserData(1));
    }
}
