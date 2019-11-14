package com.mimu.simple.java.klass;


import com.mimu.simple.java.klass.classloader.FileSystemClassLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * author: mimu
 * date: 2019/2/7
 */
public class ThreadOne implements Runnable {

    private String threadTwo = "com.mimu.simple.java.klass.ThreadTwo";
    private String userDir = System.getProperty("user.dir");

    @Override
    public void run() {
        try {
            FileSystemClassLoader classLoader = new FileSystemClassLoader(userDir);

            Class<?> threadTwo = classLoader.loadClass(this.threadTwo);
            /**
             * ThreadOne 的类加载器 说明 见 ThreadZero 中的 注释
             */
            System.out.println(Thread.currentThread().getName() + ":c1:" + ThreadOne.class.getClassLoader());
            /**
             * ThreadTwo 的类 是由 ThreadOne 中的 run() 方法执行的，而 ThreadOne 是在自定义的 列加载器中加载的，
             * 同时 该方法中 又构造了一个自定义的 类加载器来 加载 ThreadTwo，所以 ThreadTwo 的类加载器同样是 自定义的类加载器
             * 并且和 ThreadOne 的不是同一个 类加载器
             */
            System.out.println(Thread.currentThread().getName() + ":c1:" + threadTwo.getClassLoader());
            /**
             * 由于我们 设置了 Thread.currentThread().setContextClassLoader() 的加载器，所以
             *  threadTwo 的类加载器 以及 threadTwo.run() 方法中的 所有的 输出的 类加载器 都是 我们在此设置的
             *  列加载器
             */
            Thread.currentThread().setContextClassLoader(classLoader);
            System.out.println(Thread.currentThread().getName() + ":c1:" + Thread.currentThread().getContextClassLoader());
            System.out.println();
            Thread thread = new Thread((Runnable) threadTwo.newInstance());
            thread.start();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

}
