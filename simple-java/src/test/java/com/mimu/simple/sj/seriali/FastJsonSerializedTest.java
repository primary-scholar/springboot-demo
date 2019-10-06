package com.mimu.simple.sj.seriali;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import org.junit.Test;

/**
 * author: mimu
 * date: 2019/10/5
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
}
