package com.mimu.simple.spring.mybatis.service;

import com.mimu.simple.spring.mybatis.CommonService;
import com.mimu.simple.spring.mybatis.config.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * author: mimu
 * date: 2019/10/29
 */
public class MybatisServiceDetailTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        CommonService commonService = configApplicationContext.getBean(CommonService.class);
        System.out.println(commonService.finUserById(1));
    }
}
