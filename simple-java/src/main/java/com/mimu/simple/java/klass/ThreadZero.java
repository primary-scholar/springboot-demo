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
    private String threadOne = "com.mimu.simple.klass.ThreadOne";
    private String classSuffix = ".class";
    private String userDir = System.getProperty("user.dir");

    @Override
    public void run() {
        try {
            copyClass(threadOne);
            FileSystemClassLoader classLoader = new FileSystemClassLoader(userDir);
            Class<?> threadOne = classLoader.loadClass(this.threadOne);
            System.out.println(Thread.currentThread().getName() + ":zero:" + ThreadZero.class.getClassLoader());
            System.out.println(Thread.currentThread().getName() + ":zero:" + Thread.currentThread().getContextClassLoader());
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
