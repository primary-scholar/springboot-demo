package com.mimu.simple.sd.consumer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * author: mimu
 * date: 2019/10/15
 */
@Configuration
@Import(value = {ServiceConsumerConfig.class})
@EnableWebMvc
public class ApplicationConsumerConfig {

}
