package com.mimu.simple.spring.szc.inject.test.ann;

import com.mimu.simple.spring.szc.inject.ann.EnableZKCenter;
import org.springframework.context.annotation.Configuration;

/**
 author: mimu
 date: 2020/4/25
 */

@Configuration
@EnableZKCenter(basePackageClasses = ZKPropertyModel.class)
public class ApplicationConfig {
}
