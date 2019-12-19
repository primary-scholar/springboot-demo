package com.mimu.simple.springboot.mybatis.multipledb.config;

import com.mimu.simple.springboot.mybatis.multipledb.aspects.CustomDataSourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 author: mimu
 date: 2019/12/19
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfig {

    @Bean
    public CustomDataSourceAspect customDataSourceAspect(){
        return new CustomDataSourceAspect();
    }
}
