package com.mimu.simple.spring.annotation.sourceplace.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 author: mimu
 date: 2019/12/16
 */
@Component
public class DomainA {
    @Value("${source.field}")
    private String domain;

    @Override
    public String toString() {
        return "DomainA{" +
                "domain='" + domain + '\'' +
                '}';
    }
}
