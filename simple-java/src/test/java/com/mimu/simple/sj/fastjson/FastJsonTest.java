package com.mimu.simple.sj.fastjson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * author: mimu
 * date: 2019/7/23
 */
public class FastJsonTest {

    @Test
    public void info() {
        People people = new People(10, "name");
        System.out.println(JSONObject.toJSONString(people));

        String peos = "{\"aName\":\"anamela\",\"age\":11}";
        People people1 = JSONObject.toJavaObject(JSONObject.parseObject(peos), People.class);
        System.out.println(JSONObject.toJSONString(people1));

        String peos1 = "{\"bName\":\"bnamela\",\"age\":12}";
        People people2 = JSONObject.toJavaObject(JSONObject.parseObject(peos1), People.class);
        System.out.println(JSONObject.toJSONString(people2));
        System.out.println();
    }

    @Test
    public void info1() {
        People people = new People(1, "aaa");
        String jsonString = JSONObject.toJSONString(people);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        People people1 = JSONObject.toJavaObject(jsonObject, People.class);
        System.out.println(people1);
    }

    static class People {
        private int id;
        private String name;

        People() {
        }

        People(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "People{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
