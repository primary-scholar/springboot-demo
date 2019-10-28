package com.mimu.simple.spring.hystrix.config;

import com.mimu.simple.spring.hystrix.HystrixApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * author: mimu
 * date: 2019/8/30
 */
@Configuration
@ComponentScan(basePackageClasses = HystrixApplication.class)
@Import(HystrixConfig.class)
public class ApplicationConfig {
}
