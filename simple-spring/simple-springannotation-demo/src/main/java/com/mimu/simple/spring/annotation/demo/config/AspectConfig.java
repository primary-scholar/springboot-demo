package com.mimu.simple.spring.annotation.demo.config;

import com.mimu.simple.spring.annotation.demo.core.SimpleLoggerAroundAspectBean;
import com.mimu.simple.spring.annotation.demo.core.SimpleLoggerAspectBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfig {

    @Bean
    public SimpleLoggerAspectBean loggerAspect() {
        return new SimpleLoggerAspectBean();
    }

    @Bean
    public SimpleLoggerAroundAspectBean loggerAroundAspect() {
        return new SimpleLoggerAroundAspectBean();
    }
}
