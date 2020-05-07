package com.mimu.simple.spring.szc.inject.annotation.app;

import com.mimu.simple.spring.szc.inject.annotation.EnableZKCenter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2018/12/4
 */
@Configuration
@ComponentScan(basePackageClasses = ZKPropertyModel.class)
@EnableZKCenter
public class ApplicationConfig {
}
