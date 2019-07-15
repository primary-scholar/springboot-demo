package com.mimu.simple.sj.klass;


import com.mimu.simple.sj.klass.classloader.FileSystemClassLoader;

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

    private String dot = ".";
    private String separator = File.separator;
    private String threadTwo = "com.mimu.simple.klass.ThreadTwo";
    private String classSuffix = ".class";
    private String userDir = System.getProperty("user.dir");

    @Override
    public void run() {
        try {
            copyClass(threadTwo);
            FileSystemClassLoader classLoader = new FileSystemClassLoader(userDir);
            Thread.currentThread().setContextClassLoader(classLoader);
            Class<?> threadTwo = classLoader.loadClass(this.threadTwo);
            System.out.println(Thread.currentThread().getName() + ":c1:" + ThreadOne.class.getClassLoader());
            System.out.println(Thread.currentThread().getName() + ":c1:" + Thread.currentThread().getContextClassLoader());
            System.out.println(Thread.currentThread().getName() + ":c1:" + threadTwo.getClassLoader());
            System.out.println();
            Thread thread = new Thread((Runnable) threadTwo.newInstance());
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
