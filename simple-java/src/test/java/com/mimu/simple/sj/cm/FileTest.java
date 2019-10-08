package com.mimu.simple.sj.cm;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * author: mimu
 * date: 2019/10/8
 */
public class FileTest {

    @Test
    public void info(){
        Path path = Paths.get(this.getClass().getName()).toAbsolutePath().getParent();
        String s = Paths.get(this.getClass().getName()).toString().replaceAll("\\.", "\\/");
        Path resolve = path.resolve(s);
        System.out.println(resolve.toString().concat(".class"));
    }
}
