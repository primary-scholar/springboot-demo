package com.mimu.simple.spring.dubbo.model;


import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

/**
 * author: mimu
 * date: 2019/9/27
 */
public class HelloDataX extends HelloData {
    private static final long serialVersionUID = 2342272583882554053L;
    private int id;

    public HelloDataX() {
    }

    public HelloDataX(String name, int id) {
        super(name);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HelloDataX that = (HelloDataX) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
