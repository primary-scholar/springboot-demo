package com.mimu.simple.spring.szc.inject.test.ann;

import com.mimu.simple.spring.szc.inject.ann.EnableZKCenter;
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
