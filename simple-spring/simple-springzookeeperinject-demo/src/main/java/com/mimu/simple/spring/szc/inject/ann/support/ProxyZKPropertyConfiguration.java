package com.mimu.simple.spring.szc.inject.ann.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 author: mimu
 date: 2020/4/28
 */
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class ProxyZKPropertyConfiguration extends AbstractPropertyConfiguration {

    @Bean
    public BeanFactoryZKPropertyAttributeSourceAdvisor zkPropertyAttributeSourceAdvisor() {
        BeanFactoryZKPropertyAttributeSourceAdvisor advisor = new BeanFactoryZKPropertyAttributeSourceAdvisor();
        advisor.setZkPropertyArrtibuteSource(propertyArrtibuteSource());
        return advisor;
    }

    @Bean
    public ZKPropertyArrtibuteSource propertyArrtibuteSource() {
        return new AnnotationZkPropertyAttributeSource();
    }

    @Bean
    public ZKPropertyInterceptor propertyInterceptor() {
        ZKPropertyInterceptor interceptor = new ZKPropertyInterceptor();
        return interceptor;
    }

}
