package com.mimu.simple.sd.provider.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * author: mimu
 * date: 2019/9/17
 */
@Configuration
@ComponentScan(value = "com.mimu.simple.sd.provider")
//@ImportResource(value = {"classpath:spring-dubbo-provider-annotation.xml"})
@ImportResource(value = {"classpath:spring-dubbo-provider.xml"})
@Import(value = {DataSourceConfig.class})
@EnableWebMvc
public class ApplicationServerConfig {
}
