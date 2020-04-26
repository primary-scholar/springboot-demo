package com.mimu.simple.spring.file.model;

/**
 * author: mimu
 * date: 2018/12/3
 */
public class ObjectB {
    private long id;
    private String desc;
    private String bName;
    private ObjectBFieldFirstExtendsObjectAFieldFirst fieldFirst;
    private ObjectBFieldSecond fieldSecond;
    private InnerClass innerClass;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public ObjectBFieldFirstExtendsObjectAFieldFirst getFieldFirst() {
        return fieldFirst;
    }

    public void setFieldFirst(ObjectBFieldFirstExtendsObjectAFieldFirst fieldFirst) {
        this.fieldFirst = fieldFirst;
    }

    public ObjectBFieldSecond getFieldSecond() {
        return fieldSecond;
    }

    public void setFieldSecond(ObjectBFieldSecond fieldSecond) {
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
        return "ObjectB{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                ", bName='" + bName + '\'' +
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
