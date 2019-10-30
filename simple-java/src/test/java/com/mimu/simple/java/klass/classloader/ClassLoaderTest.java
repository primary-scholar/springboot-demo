package com.mimu.simple.java.klass.classloader;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class ClassLoaderTest {

    private String pringNothing = "printNothing";
    private String sameClass = "com.mimu.common.classloader.SameClass";

    @Test
    public void printInfo() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazz = Class.forName(sameClass);
        Class<?> clazzEquivalent = Class.forName(sameClass, true, this.getClass().getClassLoader());
        /**
         * the loading of clazz is equivalent with classEquivalent so @Code  this.getClass().getClassLoader is current classLoader
         */
        Object builderDemo = clazz.newInstance();
        Method printInfo = clazz.getMethod(pringNothing);
        printInfo.invoke(builderDemo, (Object[]) null);
    }


    @Test
    public void printAnotherInfo() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> clazzOne = Thread.currentThread().getContextClassLoader().loadClass(sameClass);
        Object builderDemoOne = clazzOne.newInstance();
        Class<?> clazz = Class.forName(sameClass);
        Method printInfo = clazz.getMethod(pringNothing);
        printInfo.invoke(builderDemoOne, (Object[]) null);
    }

    @Test
    public void printOtherInfo() {
        /**
         * the former c2 sentence illustrate that if you not set the thread context class loader the class loader is equal with current class loader
         */
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader());
    }

    @Test
    public void printInfoAgain() {
        ClassLoader loader = this.getClass().getClassLoader();
        while (loader != null) {
            System.out.println(loader.toString());
            loader = loader.getParent();
        }
    }
}
