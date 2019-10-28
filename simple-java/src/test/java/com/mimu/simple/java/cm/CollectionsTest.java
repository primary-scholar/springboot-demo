package com.mimu.simple.java.cm;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

/**
 * author: mimu
 * date: 2019/9/23
 */
public class CollectionsTest {

    @Test
    public void info(){
        Map<Long,String> longStringMap = Collections.emptyMap();
        System.out.println(longStringMap.get(1l));
    }
}
