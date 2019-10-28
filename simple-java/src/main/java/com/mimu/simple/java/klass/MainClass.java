package com.mimu.simple.java.klass;

/**
 * author: mimu
 * date: 2019/2/7
 */
public class MainClass {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+":main:"+Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.currentThread().getName()+":main:"+MainClass.class.getClassLoader());
        System.out.println();
        Thread thread = new Thread(new ThreadZero());
        thread.start();
    }
}
