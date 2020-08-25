package com.mimu.simple.java.klass.ext;

public class ChildKlass  extends ParentKlass{

    @Override
    public void init() {
        super.init();
        System.out.println("childKlass init");
    }

    @Override
    protected void process2() {
        super.process2();
        System.out.println("childKlass p2");
    }

    public static void main(String[] args) {
        new ChildKlass().init();
    }
}
