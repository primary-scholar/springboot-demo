package com.mimu.simple.spring.szc.inject.test.annotation;

import com.mimu.simple.spring.szc.inject.annotation.EnableZKProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2018/12/4
 */
@Configuration
@ComponentScan(basePackageClasses = ZKPropertyModel.class)
@EnableZKProperty
public class ApplicationConfig {
}
