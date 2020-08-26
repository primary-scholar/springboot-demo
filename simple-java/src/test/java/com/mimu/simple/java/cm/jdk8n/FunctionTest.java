package com.mimu.simple.java.cm.jdk8n;

import java.util.function.Function;

public class FunctionTest {

    public static void main(String[] args) {
        Function<Integer, Integer> addFunction = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + integer;
            }
        };

        Function<Integer, Integer> multiplyFunction = integer -> integer * integer;

        System.out.println(addFunction.apply(3));
        System.out.println(multiplyFunction.apply(3));
        System.out.println(addFunction.compose(multiplyFunction).apply(3));
        System.out.println(addFunction.andThen(multiplyFunction).apply(3));

    }
}
