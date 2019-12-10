package com.mimu.simple.spring.dubbo.consumer.config;

import com.mimu.simple.spring.dubbo.consumer.AppSpringDubboConsumer;
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
@ImportResource(value = {"classpath:dubbo-consumer.xml"})
@ComponentScan(basePackageClasses = AppSpringDubboConsumer.class)
@EnableWebMvc
public class ApplicationConsumerConfig {

}
