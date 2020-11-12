package com.mimu.simple.java.cm;


import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

/**
 * author: mimu
 * date: 2019/7/12
 */
public class NetworkTest {

    @Before
    public void init(){
        Properties properties = new Properties();
        properties.put("a","a");
        properties.put("b","b");
        System.setProperties(properties);
    }

    @Test
    public void info(){
        System.out.println(System.getProperty("a"));
        System.out.println(System.getProperty("b"));
    }
}
