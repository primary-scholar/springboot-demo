package com.mimu.simple.spring.file.model;

/**
 author: mimu
 date: 2019/12/16
 */
public class SourceModelA {
    private String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "SourceModelA{" +
                "domain='" + domain + '\'' +
                '}';
    }
}
