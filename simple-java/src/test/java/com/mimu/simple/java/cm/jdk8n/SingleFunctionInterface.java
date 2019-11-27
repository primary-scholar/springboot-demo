package com.mimu.simple.java.cm.jdk8n;

/**
 author: mimu
 date: 2019/11/27
 */
@FunctionalInterface
public interface SingleFunctionInterface {
    int add();

    /**
     * FunctionalInterface 只允许有一个 抽象方法 如 上述add() ，
     */
    /*void minus();*/

    default void test() {
    }


    /**
     * 接口中 可提供方法，default 表示 该方法为 默认方法
     * @return
     */
    default int plus() {
        return 1;
    }



    static int sTest() {
        return 1;
    }


}
