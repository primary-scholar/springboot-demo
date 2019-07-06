package com.mimu.simple.sj.klass;

import com.mimu.simple.klass.classloader.SameClass;

/**
 * author: mimu
 * date: 2019/2/7
 */
public class ThreadTwo implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+":c2:"+ThreadTwo.class.getClassLoader());
        System.out.println(Thread.currentThread().getName()+":c2:"+ Thread.currentThread().getContextClassLoader());
        System.out.println(SameClass.class.getClassLoader());
    }
}
