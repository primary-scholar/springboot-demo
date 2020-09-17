package com.mimu.simple.java.fastjson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * author: mimu
 * date: 2019/7/23
 */
public class FastJsonTest {

    @Test
    public void info() {
        People people = new People();
        people.setId(10);
        people.setName("people");
        System.out.println(JSONObject.toJSONString(people));

        String peos = "{\"aName\":\"anamela\",\"id\":11}";
        People people1 = JSONObject.toJavaObject(JSONObject.parseObject(peos), People.class);
        System.out.println(JSONObject.toJSONString(people1));

        String peos1 = "{\"name\":\"bnamela\",\"id\":12,\"role\":\"worker\"}";
        People people2 = JSONObject.toJavaObject(JSONObject.parseObject(peos1), People.class);
        System.out.println(JSONObject.toJSONString(people2));

        String peos12 = "{\"name\":\"bnamela\",\"id\":12,\"role\":\"worker\"}";
        People people121 = JSONObject.toJavaObject(JSONObject.parseObject(peos12), Worker.class);
        System.out.println(JSONObject.toJSONString(people121));
    }

    static class People {
        private int id;
        private String name;

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
    }

    public static class Worker extends People{
        private String role;

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}
