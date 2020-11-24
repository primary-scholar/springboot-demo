package com.mimu.simple.java.cm.jdk8n;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * author: mimu
 * date: 2019/11/27
 */
public class JDK8FunctionTest {

    private final List<Integer> parameterList = new ArrayList<>();

    @Before
    public void populate() {
        for (int i = 0; i < 5; i++) {
            parameterList.add(i);
        }
    }

    @Test
    public void info() {
        Map<Integer, Object> collect = parameterList.stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
        System.out.println(collect);
        Map<String, Object> collect1 = parameterList.stream().collect(Collectors.toMap(String::valueOf, Function.identity()));
        System.out.println(collect1);
    }
}
