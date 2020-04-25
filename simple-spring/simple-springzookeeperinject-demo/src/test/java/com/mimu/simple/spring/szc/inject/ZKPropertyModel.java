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

    @ZKReference(key = "abc", value = "asd")
    private DynamicStringProperty age;

    private DynamicStringProperty num;

    @ZKReference(key = "abc", value = "asd")
    public void setNum(DynamicStringProperty num) {
        this.num = num;
    }

    public String getAge() {
        return age.get();
    }

    public String getNum() {
        return num.get();
    }
}
