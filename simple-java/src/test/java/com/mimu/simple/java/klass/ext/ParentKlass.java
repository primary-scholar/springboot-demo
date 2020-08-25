package com.mimu.simple.java.klass.ext;

public class ParentKlass {

    public void init() {
        System.out.println("parent init");
        process1();
        process2();
        process3();
    }


    protected void process1() {
        System.out.println("parent p1");
    }


    protected void process2() {
        System.out.println("parent p2");
    }


    protected void process3() {
        System.out.println("parent p3");
    }

}
