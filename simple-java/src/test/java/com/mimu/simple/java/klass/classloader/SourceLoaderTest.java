package com.mimu.simple.java.klass.classloader;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class SourceLoaderTest {

    private String resourcesName = "test.txt";
    private String dot = ".";
    private String separator = File.separator;


    @Test
    public void getFileTest() {
        System.out.println(getFile(resourcesName));
    }

    /**
     * contrast the loadSource and loadSourceAgain you will find the the difference between the .class.getClassLoader.getResourceAsStream()
     * and the .class.getResourceAsStream(),
     * the first c1 apply the source file in java.class.path
     * and the latter apply that the source file has the same directory with the .class file
     *
     * it also illustrate that the classloader load the class with the package name.
     */

    @Test
    public void loadSource() {
        generateFile(resourcesName);
        System.out.println(SourceLoaderTest.class.getClassLoader().getResourceAsStream(resourcesName));
        System.out.println(SourceLoaderTest.class.getPackage());
    }

    @Test
    public void loadSourceAgain() {
        generateFile(SourceLoaderTest.class.getPackage().toString().split(" ")[1], resourcesName);
        System.out.println(SourceLoaderTest.class.getResourceAsStream(resourcesName));
    }


    private void generateFile(String dir, String fileName) {
        generateFile(packageToPath(dir).concat(separator).concat(fileName));
    }

    private void generateFile(String fileName) {
        String path = getFile(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)))) {
            writer.write(resourcesName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFile(String fileName) {
        return getCurrentClassPath().concat(File.separator).concat(fileName);
    }

    private String packageToPath(String packages) {
        return packages.replace(dot, separator);
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
