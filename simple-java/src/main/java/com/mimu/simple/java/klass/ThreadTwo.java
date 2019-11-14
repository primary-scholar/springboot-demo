package com.mimu.simple.java.klass;


/**
 * author: mimu
 * date: 2019/2/7
 */
public class ThreadTwo implements Runnable {

    @Override
    public void run() {
        /**
         * 该方法中的 输出 参见 ThreadOne 中的 说明
         */
        System.out.println(Thread.currentThread().getName() + ":c2:" + ThreadTwo.class.getClassLoader());
        System.out.println(Thread.currentThread().getName() + ":c2:" + Thread.currentThread().getContextClassLoader());
    }
}
