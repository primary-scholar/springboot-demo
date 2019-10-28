package com.mimu.simple.spring.dubbo.consumer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * author: mimu
 * date: 2019/10/15
 */
@Configuration
//@Import(value = {ServiceConsumerConfig.class})
@ImportResource(value = {"classpath:spring-dubbo-consumer.xml"})
@ComponentScan(basePackages = {"com.mimu.simple.sd.consumer"})
@EnableWebMvc
public class ApplicationConsumerConfig {

}
