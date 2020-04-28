package com.mimu.simple.spring.szc.inject.ann.support;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

/**
 author: mimu
 date: 2020/4/28
 */
@Configuration
public class AbstractPropertyConfiguration implements ImportAware {
    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {

    }
}
