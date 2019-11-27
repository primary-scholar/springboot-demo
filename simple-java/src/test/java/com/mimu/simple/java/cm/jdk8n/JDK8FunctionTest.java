package com.mimu.simple.java.cm.jdk8n;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 author: mimu
 date: 2019/11/27
 */
public class JDK8FunctionTest {

    private List<Integer> parameterList;

    @Before
    public void populate() {
        for (int i = 0; i < 5; i++) {
            parameterList.add(i);
        }
    }


}
