package com.mimu.simple.spring.szc.inject;

import com.mimu.simple.spring.szc.inject.annotation.ZKReference;
import com.netflix.config.DynamicStringProperty;
import org.springframework.stereotype.Component;

/**
 author: mimu
 date: 2020/4/25
 */
@Component
public class ZKPropertyModel {

    @ZKReference(key = "abcd", value = "asd")
    private DynamicStringProperty age;

    @ZKReference(key = "", value = "")
    private DynamicStringProperty num;

    public String getAge() {
        return age.get();
    }

    public String getNum() {
        return num.get();
    }
}
