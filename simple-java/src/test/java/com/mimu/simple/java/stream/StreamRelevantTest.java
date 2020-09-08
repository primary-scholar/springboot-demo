package com.mimu.simple.java.stream;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
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

    @Test
    public void info1() {
        List list = new LinkedList<Person>();
        list.add(new Person(1, "a", true));
        list.add(new Person(2, "b", true));
        list.add(new Person(1, "a", false));
        // distinct 根据 hashcode 和 equal 进行 去重
        list = (ArrayList) list.stream().distinct().collect(Collectors.toList());
        list.forEach(o -> System.out.println(o));
    }

    public static class Person {
        private int age;
        private String name;
        private boolean flag;

        public Person(int age, String name, boolean flag) {
            this.age = age;
            this.name = name;
            this.flag = flag;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", flag=" + flag +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person)) return false;
            Person person = (Person) o;
            return age == person.age &&
                    flag == person.flag &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name, flag);
        }
    }
}
