package com.mimu.simple.java.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StreamRelevantTest {

    @Test
    public void info() {
        ArrayList list = new ArrayList<Integer>();
        list.add(11);
        list.add(12);
        list.add(13);
        Object collect = list.parallelStream().map(o -> ((Integer) o) * 10).collect(Collectors.toList());
        System.out.println(collect);
    }
}
