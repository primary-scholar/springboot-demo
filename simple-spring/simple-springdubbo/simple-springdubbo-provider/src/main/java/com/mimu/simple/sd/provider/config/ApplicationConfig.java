package com.mimu.simple.sd.provider.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * author: mimu
 * date: 2019/9/17
 */
@Configuration
@ComponentScan(value = "com.mimu.simple.sd.provider")
@Import(value = {DataSourceConfig.class, ServiceProviderConfig.class})
public class ApplicationConfig {
}
