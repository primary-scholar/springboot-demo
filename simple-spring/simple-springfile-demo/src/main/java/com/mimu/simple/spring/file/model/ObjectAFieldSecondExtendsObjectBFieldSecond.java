package com.mimu.simple.spring.file.model;

/**
 * author: mimu
 * date: 2019/10/16
 */
public class ObjectAFieldSecondExtendsObjectBFieldSecond extends ObjectBFieldSecond {
    protected int oaFieldSecond;

    public int getOaFieldSecond() {
        return oaFieldSecond;
    }

    public void setOaFieldSecond(int oaFieldSecond) {
        this.oaFieldSecond = oaFieldSecond;
    }

    @Override
    public String toString() {
        return "ObjectAFieldSecondExtendsObjectBFieldSecond{" +
                "oaFieldSecond=" + oaFieldSecond +
                ", obFieldSecond=" + obFieldSecond +
                '}';
    }
}
