package com.mimu.simple.springboot.zkr.zkconfig;

import com.mimu.simple.springboot.zkr.annotation.interceptor.ZKPropertyAspectSupport;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnBean(ZKPropertyAspectSupport.class)
@EnableConfigurationProperties(ZookeeperConfigProperties.class)
public class ZookeeperConfigCenterAutoConfiguration {

    @Bean
    public void ZKCenterResource(){

    }
}
