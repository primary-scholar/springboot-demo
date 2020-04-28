package com.mimu.simple.spring.szc.inject.test.ann.app;

import com.mimu.simple.spring.szc.inject.ann.EnableZKCenter;
import com.mimu.simple.spring.szc.inject.ann.ZKConfigProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2018/12/4
 */
@Configuration
@ComponentScan(basePackageClasses = ZKConfigProperty.class)
@EnableZKCenter(proxyTargetClass = true)
public class ApplicationConfig {
}
