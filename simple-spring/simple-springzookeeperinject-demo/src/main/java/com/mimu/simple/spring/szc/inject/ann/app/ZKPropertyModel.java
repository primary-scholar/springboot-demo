package com.mimu.simple.spring.szc.inject.ann.app;


import com.mimu.simple.spring.szc.inject.ann.ZKReference;
import com.netflix.config.DynamicStringProperty;
import org.springframework.stereotype.Component;

/**
 author: mimu
 date: 2020/4/25
 */
@Component
public class ZKPropertyModel {

    private int age;
    private String name;
    private boolean aBoolean;
    private String tmp;

    @ZKReference(key = "abc",value = "1")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @ZKReference(key = "abd",value = "1")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ZKReference(key = "abe",value = "1")
    public boolean isaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }
}
