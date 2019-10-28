package com.mimu.simple.spring.file.model;

/**
 * author: mimu
 * date: 2019/6/1
 */
public class ObjectA {
    private int id;
    private String desc;
    private String aName;
    private ObjectAFieldFirst fieldFirst;
    private ObjectAFieldSecondExtendsObjectBFieldSecond fieldSecond;
    private ObjectA.InnerClass innerClass;

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ObjectAFieldFirst getFieldFirst() {
        return fieldFirst;
    }

    public void setFieldFirst(ObjectAFieldFirst fieldFirst) {
        this.fieldFirst = fieldFirst;
    }

    public ObjectAFieldSecondExtendsObjectBFieldSecond getFieldSecond() {
        return fieldSecond;
    }

    public void setFieldSecond(ObjectAFieldSecondExtendsObjectBFieldSecond fieldSecond) {
        this.fieldSecond = fieldSecond;
    }

    public InnerClass getInnerClass() {
        return innerClass;
    }

    public void setInnerClass(InnerClass innerClass) {
        this.innerClass = innerClass;
    }

    @Override
    public String toString() {
        return "ObjectA{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", aName='" + aName + '\'' +
                ", fieldFirst=" + fieldFirst +
                ", fieldSecond=" + fieldSecond +
                ", innerClass=" + innerClass +
                '}';
    }

    public static class InnerClass{
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
