package com.mimu.simple.ssh.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * author: mimu
 * date: 2019/8/30
 */
@Configuration
@ComponentScan(basePackages = {"com.mimu.simple.ssh"})
@Import(HystrixConfig.class)
public class ApplicationConfig {
}
