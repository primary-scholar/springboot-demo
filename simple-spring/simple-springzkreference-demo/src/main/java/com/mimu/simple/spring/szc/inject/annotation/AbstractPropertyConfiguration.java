package com.mimu.simple.spring.szc.inject.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

/**
 author: mimu
 date: 2020/4/28
 */
@Configuration(proxyBeanMethods = false)
public class AbstractPropertyConfiguration implements ImportAware {
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {

    }
}
