package com.mimu.simple.spring.file.model;

/**
 * author: mimu
 * date: 2019/10/15
 */
public class ObjectBFieldFirstExtendsObjectAFieldFirst extends ObjectAFieldFirst {
    private float obFieldFirst;

    public float getObFieldFirst() {
        return obFieldFirst;
    }

    public void setObFieldFirst(float obFieldFirst) {
        this.obFieldFirst = obFieldFirst;
    }

    @Override
    public String toString() {
        return "ObjectBFieldFirstExtendsObjectAFieldFirst{" +
                "obFieldFirst=" + obFieldFirst +
                ", oaFieldFirst=" + oaFieldFirst +
                '}';
    }
}
