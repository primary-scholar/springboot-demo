package com.mimu.simple.java.klass.classloader;

import java.io.*;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class FileSystemClassLoader extends ClassLoader {

    private String classDir;

    public FileSystemClassLoader(String rootDir) {
        this.classDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classData = getClassData(name);
            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //throw new ClassNotFoundException();
        return null;
    }

    private byte[] getClassData(String className) throws IOException {
        String path = classNameToPath(className);
        InputStream inputStream = new FileInputStream(path);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bufferSize = 4096, bufferRead;
        byte[] buffer = new byte[bufferSize];
        while ((bufferRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bufferRead);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private String classNameToPath(String className) {
        return classDir + File.separator + className.replace(".", File.separator) + ".class";
    }
}
