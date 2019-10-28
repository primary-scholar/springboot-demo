package com.mimu.simple.java.klass.classloader;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Attention!!!
 * <p>
 * Pay attention to the result of these method and think twice why;
 */

/**
 * author: mimu
 * date: 2018/12/4
 */
public class FileSystemClassLoaderTest {

    private String dot = ".";
    private String separator = File.separator;
    private String sameClass = "com.mimu.simple.java.klass.classloader.SameClass";
    private String setSameClass = "setSameClass";
    private String classSuffix = ".class";
    private String userDir = System.getProperty("user.dir");


    @Test
    public void print(){
        System.out.println(FileSystemClassLoader.getSystemClassLoader());
    }

    @Test
    public void printInfo() throws ClassNotFoundException {
        String currentClassPath = getCurrentClassPath();
        FileSystemClassLoader classLoaderOne = new FileSystemClassLoader(currentClassPath);
        System.out.println(classLoaderOne.getClass().getClassLoader());
        System.out.println();

        /**
         * here we use loadClass() to load our class .thus we can ensure that
         * we use the parent classLoader to load our class (according to the parent delegation),
         * more information look into the source of classLoader
         */
        Class<?> clazzOne = classLoaderOne.loadClass(sameClass);
        System.out.println(clazzOne.getClassLoader());
        System.out.println();

        /**
         * here we use findClass() to load our class,because we overwrite the findClass() method in
         * FileSystemClassLoader, so it will use our self-define classLoader
         * load the class,more information look into the source of classLoader
         *
         * <p>
         *     according to the sout info you will find the difference of findClass() and loadClass()
         * </p>
         */
        FileSystemClassLoader classLoaderTwo = new FileSystemClassLoader(currentClassPath);
        Class<?> clazzTwo = classLoaderTwo.findClass(sameClass);
        System.out.println(clazzTwo.getClassLoader());
        System.out.println();

    }

    @Test
    public void ConstrastOneTwo_One() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        System.out.println(Class.class.getClassLoader());
        FileSystemClassLoader classLoaderOne = new FileSystemClassLoader(getCurrentClassPath());
        System.out.println(classLoaderOne.getClass().getClassLoader());
        /**
         * here we use loadClass() to load the sameClass
         */
        Class<?> clazzTwo = classLoaderOne.loadClass(sameClass);
        System.out.println(clazzTwo.getClassLoader());
        Method setSameClazzTwo = clazzTwo.getDeclaredMethod(setSameClass, clazzTwo);
    }

    @Test
    public void ConstrastOneTwo_Two() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        FileSystemClassLoader classLoaderOne = new FileSystemClassLoader(getCurrentClassPath());
        /**
         * here we use findClass() to load the sameClass
         */
        Class<?> clazzTwo = classLoaderOne.findClass(sameClass);
        System.out.println(clazzTwo.getClassLoader());
        System.out.println(com.mimu.simple.java.klass.classloader.SameClass.class.getClassLoader());
        /**
         * Attention!!!
         * the next line sentence will be wrong !
         *
         * here the classloader of com.mimu.common.classloader.SameClass is appClassLoader
         * while the classLoader of clazzTwo is FileSystemClassLoader which is self-definition c1
         * so the next sentence can't be compile
         * the com.mimu.common.classloader.SameClass and clazzTwo is different namespace.
         */
        Method setSameClazzTwo = clazzTwo.getDeclaredMethod(setSameClass, com.mimu.simple.java.klass.classloader.SameClass.class);
    }

    @Test
    public void printNothing() throws ClassNotFoundException {
        FileSystemClassLoader classLoaderOne = new FileSystemClassLoader(getCurrentClassPath());
        Thread.currentThread().setContextClassLoader(classLoaderOne);
        System.out.println(this.getClass().getClassLoader());
        System.out.println();
        System.out.println(Thread.currentThread().getContextClassLoader());
        Class<?> clazz =Thread.currentThread().getContextClassLoader().loadClass(sameClass);
        /**
         * Attention!!
         *  Why!!!
         * here the SameClass's classLoader is also AppClassLoader
         */
        System.out.println(clazz.getClassLoader());
        System.out.println();
        System.out.println(com.mimu.simple.java.klass.classloader.SameClass.class.getClassLoader());
    }

    @Test
    public void contrastTwo_One() throws ClassNotFoundException {
        Class<?> clazzOne = Thread.currentThread().getContextClassLoader().loadClass(sameClass);
        System.out.println(clazzOne);
        System.out.println(clazzOne.getClassLoader());
        System.out.println();
        FileSystemClassLoader classLoaderOne = new FileSystemClassLoader(getCurrentClassPath());
        Class<?> clazzTwo = classLoaderOne.loadClass(sameClass);
        System.out.println(clazzTwo);
        System.out.println(clazzTwo.getClassLoader());
        System.out.println();
        Thread.currentThread().setContextClassLoader(classLoaderOne);
        Class<?> clazzThree = Thread.currentThread().getContextClassLoader().loadClass(sameClass);
        System.out.println(clazzThree.getClassLoader());
        System.out.println();

        /**
         * here we reConfig the contextClassLoader so if we user the CurrentTread.contextClassLoader
         * load the class the class we loaded's classLoader is the classLoader which we reConfig c1;
         *
         * While if we sout the class which is qualifier by all package name ,the classloder is AppClassLoader
         *  so this point was confused with me!
         */
        System.out.println(com.mimu.simple.java.klass.classloader.SameClass.class.getClassLoader());
    }

    /**
     * Attention!!!
     * <p>
     * before run this case you should ensure that the SameClass.class is exists is classPath and
     * after execute the copyClass you should ensure delete the SameClass.class
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void contrastTwo_Two() throws IOException, ClassNotFoundException {
        copyClass();
        try {
            Class<?> clazzOne = Thread.currentThread().getContextClassLoader().loadClass(sameClass);
            System.out.println(clazzOne);
            System.out.println(clazzOne.getClassLoader());
            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getContextClassLoader());
        FileSystemClassLoader classLoaderOne = new FileSystemClassLoader(userDir);
        Class<?> clazzTwo = classLoaderOne.loadClass(sameClass);
        System.out.println(clazzTwo);
        System.out.println(clazzTwo.getClassLoader());
        System.out.println();
        Thread.currentThread().setContextClassLoader(classLoaderOne);
        Class<?> clazzThree = Thread.currentThread().getContextClassLoader().loadClass(sameClass);
        System.out.println(clazzThree.getClassLoader());
        System.out.println();

    }

    private void copyClass() throws IOException {
        Path source = Paths.get(getCurrentClassPath()).resolve(sameClass.replace(dot, separator).concat(classSuffix));
        Path dest = Paths.get(userDir).resolve(sameClass.replace(dot, separator).concat(classSuffix));
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
