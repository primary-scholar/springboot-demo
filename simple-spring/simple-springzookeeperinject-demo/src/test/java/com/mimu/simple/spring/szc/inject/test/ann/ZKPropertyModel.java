package com.mimu.simple.spring.szc.inject.test.ann;

import com.mimu.simple.spring.szc.inject.ann.ZKCenter;
import com.mimu.simple.spring.szc.inject.annotation.ZKReference;
import com.netflix.config.DynamicStringProperty;
import org.springframework.stereotype.Component;

/**
 author: mimu
 date: 2020/4/25
 */
@ZKCenter
public class ZKPropertyModel {

    @ZKReference(key = "abc", value = "asd")
    private int age;

    private String num;

    @ZKReference(key = "abc", value = "asd")
    public void setNum(String num) {
        this.num = num;
    }

    public int getAge() {
        return age;
    }

    public String getNum() {
        return num;
    }


}
