package com.mimu.simple.spring.dubbo.consumer;

import com.mimu.simple.spring.dubbo.consumer.service.CommonService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 author: mimu
 date: 2019/12/10
 */
public class ConsumerTest {

    @Test
    public void info() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"});
        CommonService bean = context.getBean(CommonService.class);
        System.out.println(bean.getUserData(1));
    }
}
