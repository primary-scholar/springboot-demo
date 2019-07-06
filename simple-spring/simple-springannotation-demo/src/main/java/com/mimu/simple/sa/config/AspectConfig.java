package com.mimu.simple.sa.config;

import com.mimu.simple.sa.core.SimpleLoggerAspectBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectConfig {

    @Bean
    @Scope(scopeName = "prototype")
    public SimpleLoggerAspectBean loggerAspect() {
        return new SimpleLoggerAspectBean();
    }
}
