package com.mimu.simple.spring.dubbo.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.Objects;

/**
 * author: mimu
 * date: 2019/9/18
 */
public class HelloData implements Serializable {
    private static final long serialVersionUID = 3970884398250763626L;
    protected String name;

    public HelloData() {
    }

    public HelloData(String name) {
        this.name = name;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HelloData helloData = (HelloData) o;
        return Objects.equals(name, helloData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
