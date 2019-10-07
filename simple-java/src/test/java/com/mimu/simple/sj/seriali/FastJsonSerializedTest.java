package com.mimu.simple.sj.seriali;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.junit.Test;

/**
 * author: mimu
 * date: 2019/10/5
 */

/**
 * FastJson 序列化过程 JSONObject.toJSONString(people)
 * 1. 根据object 获取其对应Class
 * 2。根据class信息构造SerializeBeanInfo 信息
 * 2。1 反射获取class 所有 getXXX() 或 isXXX() 方法(解析使用@JSONField的情形) 以获取序列化对应的 key
 * 2。2 根据 2。1返回的 key 信息 利用反射获取到对象的值 以获取序列化对应的 value
 * 3。根据 key 和 value 拼成 string 字符串
 * <p>
 * <p>
 * FastJson 反序列化过程
 * 1。String peos = "{\"aName\":\"anamela\",\"age\":11}"
 * 1。1 JSONObject jsonObject = JSONObject.parseObject(peos)
 * 2。People people1 = JSONObject.toJavaObject(jsonObject, People.class);
 * <p>
 * 1。1 首先把字符串 转换成 Map<String,Object>类型 组成 key：value 对儿
 * 2。1 根据 class 信息构造 ObjectDeserializer 信息
 * 2。2 反射获取class 所有 setXXX() 方法(解析使用@JSONField的情形) 以获取 反序列化后 field 接受 value 对应的 key
 * 2。3 根据 class 信息，反射生成 object 对象
 * 根据 1。1中的 Map<String,Object>的数据和 2。2 中的key进行匹配，若有对应的key 则反射method.invoke() 给field付值
 */
public class FastJsonSerializedTest {

    @Test
    public void info() {
        People people = new People();
        people.setName("name");
        people.setAge(10);
        System.out.println(JSONObject.toJSONString(people));
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(people));
        People people1 = JSONObject.toJavaObject(jsonObject, People.class);
        System.out.println(people1);
    }

    @Test
    public void info1() {
        People people = new People();
        people.setName("nameA");
        people.setAge(11);
        System.out.println(JSONObject.toJSONString(people));

        String peos = "{\"aName\":\"anamela\",\"age\":11}";
        People people1 = JSONObject.toJavaObject(JSONObject.parseObject(peos), People.class);
        System.out.println(people1);

        String peos1 = "{\"bName\":\"bnamela\",\"age\":12}";
        People people2 = JSONObject.toJavaObject(JSONObject.parseObject(peos1), People.class);
        System.out.println(people2);
    }

    public static class People {
        private String name;
        private int age;

        public People() {
        }

        public People(String name, int age) {
            this.name = name;
            this.age = age;
        }

        /**
         * 序列化时 把该对象的name属性序列化为 key:aName 属性
         * 可从 info1() 中进行验证
         *
         * @return
         */
        @JSONField(name = "aName")
        public String getName() {
            return name;
        }

        /**
         * 反序列化时 从key=bName 中获取数据填充到对象中
         * 可从 info1() 中进行验证
         *
         * @param name
         */
        @JSONField(name = "bName")
        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    @Test
    public void info2() {
        Person person = new Person("person", 11);
        String s = JSONObject.toJSONString(person);
        System.out.println(s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(JSONObject.toJavaObject(JSONObject.parseObject(s), Person.class));
    }

    /**
     * FastJson 是根据 getXXX() isXXX() 方法来获取key的 因此被序列化的 对象必须有 getXXX() 方法 或 isXXX() 方法
     */
    public static class Person {
        private String name;
        private int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        @JSONField(name = "bbb")
        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
