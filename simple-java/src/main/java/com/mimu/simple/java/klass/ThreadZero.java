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
public class ThreadZero implements Runnable {

    private String dot = ".";
    private String separator = File.separator;
    private String threadOne = "com.mimu.simple.java.klass.ThreadOne";
    private String classSuffix = ".class";
    private String userDir = System.getProperty("user.dir");

    @Override
    public void run() {
        try {
            copyClass(threadOne);
            FileSystemClassLoader classLoader = new FileSystemClassLoader(userDir);
            Class<?> threadOne = classLoader.loadClass(this.threadOne);
            /**
             * 由于 ThreadZero 是在MainClass 中加载的，并且MainClass 中并没有设置 Thread.setContextClassLoader()
             * 所以 ThreadZero 的类加载器是  Launcher$AppClassLoader，同样 Thread.currentThread().getContextClassLoader()
             * 的 类加载器 也是 Launcher$AppClassLoader
             */
            System.out.println(Thread.currentThread().getName() + ":zero:" + ThreadZero.class.getClassLoader());
            System.out.println(Thread.currentThread().getName() + ":zero:" + Thread.currentThread().getContextClassLoader());
            /**
             * 下面的方法：输出 threadOne 的类加载器 是 我们自定义的 FileSystemClassLoader 加载器，
             * 因为 虽然 我们使用的是 classLoader.loadClass()的方法加载的class(该方法同样会遵循双亲委派机制),
             * 但是 由于我们 修改了 threadOne 文件的目录，导致双亲 委派机制加载不到，所以就使用我们自定义的 加载器加载了
             */
            System.out.println(Thread.currentThread().getName() + ":zero:" + threadOne.getClassLoader());
            System.out.println();
            Thread thread = new Thread((Runnable) threadOne.newInstance());
            thread.start();
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

    }

    private void copyClass(String className) throws IOException {
        Path source = Paths.get(getCurrentClassPath()).resolve(className.replace(dot, separator).concat(classSuffix));
        Path dest = Paths.get(userDir).resolve(className.replace(dot, separator).concat(classSuffix));
        if (!dest.toFile().exists()) {
            dest.toFile().mkdirs();
        }
        Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
        Files.delete(source);
    }

    private String getCurrentClassPath() {
        String[] classPath = System.getProperty("java.class.path").split(":");
        String currentPath = "";
        for (String s : classPath) {
            if (s.startsWith(System.getProperty("user.dir")) && s.contains("classes")) {
                currentPath = s;
            }
        }
        return currentPath;
    }
}
