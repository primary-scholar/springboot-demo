package com.mimu.simple.sj.generic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * author: mimu
 * date: 2019/10/18
 */
public class GenericClazzTest {

    @Test
    public void get() {
        GenericClazz<Person> clazz = new GenericClazz<>();
        try {
            System.out.println(clazz.getB(Fruit.class));
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(clazz.getA(Person.class));
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        /**
         * 该方法 编译报错
         */
        /*try {
            System.out.println(clazz.getA(Fruit.class));
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }*/
    }

}