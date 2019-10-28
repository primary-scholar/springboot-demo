package com.mimu.simple.spring.file.model;

/**
 * author: mimu
 * date: 2018/11/23
 */
public class ObjectAFieldFirst {
    protected int oaFieldFirst;

    public int getOaFieldFirst() {
        return oaFieldFirst;
    }

    public void setOaFieldFirst(int oaFieldFirst) {
        this.oaFieldFirst = oaFieldFirst;
    }

    @Override
    public String toString() {
        return "ObjectAFieldFirst{" +
                "oaFieldFirst=" + oaFieldFirst +
                '}';
    }
}
