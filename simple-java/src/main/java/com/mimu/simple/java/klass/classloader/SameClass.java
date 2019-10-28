package com.mimu.simple.java.klass.classloader;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class SameClass {

    private String name;
    private SameClass sameClass;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SameClass getSameClass() {
        return sameClass;
    }

    public void setSameClass(SameClass sameClass) {
        this.sameClass = sameClass;
    }

    public void printInfo() {
        System.out.println("anme: " + name);
    }

    public void printNothing() {
        System.out.println("Actually this function print blalalal...");
    }
}
