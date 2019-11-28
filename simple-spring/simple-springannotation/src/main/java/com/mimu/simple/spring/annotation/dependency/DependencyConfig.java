package com.mimu.simple.spring.annotation.dependency;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author: mimu
 * date: 2019/10/17
 */
@Configuration
@ComponentScan(basePackageClasses = DependencyConfig.class)
public class DependencyConfig {
}
