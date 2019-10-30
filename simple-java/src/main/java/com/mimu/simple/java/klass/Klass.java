package com.mimu.simple.java.klass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class Klass {

    public static void main(String[] args) {
        Test test = new Test(1);
        test.run();
        try {
            Test test1 = new Test(2);
            Method method = Test.class.getMethod("run");
            while (true){
                method.invoke(test, (Object[]) null);
                method.invoke(test1, (Object[]) null);
                Thread.sleep(1000);
            }

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class Test {
        private int doamin;

        Test(int d) {
            this.doamin = d;
        }

        public void run() {
            System.out.println("value=" + doamin);
        }
    }
}
