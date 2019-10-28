package com.mimu.simple.java.klass.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * author: mimu
 * date: 2018/12/4
 */
public class NetworkClassLoader extends ClassLoader {

    private String rootUrl;

    public NetworkClassLoader(String rootUrl) {
        this.rootUrl = rootUrl;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classData = getClassData(name);
            return defineClass(name, classData, 0, classData.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException();
    }

    private byte[] getClassData(String name) throws IOException {
        String path = classNameToPath(name);
        URL url = new URL(path);
        InputStream inputStream = url.openStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bufferSize = 4096, bufferRead;
        byte[] buffer = new byte[bufferSize];
        while ((bufferRead = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bufferRead);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private String classNameToPath(String className) {
        return rootUrl + "/" + className.replace('.', '/') + ".class";
    }
}
