package com.mimu.simple.spring.dubbo.provider.config;

import com.mimu.simple.spring.dubbo.provider.AppSpringDubboServer;
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
@ComponentScan(basePackageClasses =AppSpringDubboServer.class)
//@ImportResource(value = {"classpath:spring-dubbo-provider-annotation.xml"})
@ImportResource(value = {"classpath:spring-dubbo-provider.xml"})
@Import(value = {DataSourceConfig.class})
@EnableWebMvc
public class ApplicationServerConfig {
}
