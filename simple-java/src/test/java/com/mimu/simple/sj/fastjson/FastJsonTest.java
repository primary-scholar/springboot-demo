package com.mimu.simple.sj.fastjson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * author: mimu
 * date: 2019/7/23
 */
public class FastJsonTest {
    private static String tmpstring = "jssp1{a:a}";

    @Test
    public void info(){
        People people = new People(123,"");
        people.setName(tmpstring);
        System.out.println(JSONObject.toJSONString(new People(123,getjs().toJSONString())));
        System.out.println(getjs());
        System.out.println(JSONObject.toJSON(people));
    }

    public static JSONObject getjs(){
        JSONObject result = new JSONObject();
        result.put("aaa","aaaaa");
        return result;
    }

    static class People{
        private int id;
        private String name;

        public People(int id, String name) {
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
    }
}
