package com.mimu.simple.sa.config;

import com.mimu.simple.sa.core.SimpleLoggerAroundAspectBean;
import com.mimu.simple.sa.core.SimpleLoggerAspectBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

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
