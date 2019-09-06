package com.mimu.simple.sj.fastjson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * author: mimu
 * date: 2019/7/23
 */
public class FastJsonTest {
    private static String tmpstring = "jssp1{a:a}";

    @Test
    public void info() {
        People people = new People(123, "");
        people.setName(tmpstring);
        System.out.println(JSONObject.toJSONString(new People(123, getjs().toJSONString())));
        System.out.println(getjs());
        System.out.println(JSONObject.toJSON(people));
    }

    @Test
    public void info1() {
        JSONObject result = new JSONObject();
        result.put("Wechat", "请去微信粘贴加好友我是%s，去应用市场下载【搜狐新闻】App，体验新版搜狐时刻。长按并【复制】这段话，打开【搜狐新闻】直接加我为好友");
        result.put("QQ", "请去QQ加好友我是%s，去应用市场下载【搜狐新闻】App，体验新版搜狐时刻。长按并【复制】这段话，打开【搜狐新闻】直接加我为好友。");
        result.put("Link", "我是%s，去应用市场下载【搜狐新闻】App，体验新版搜狐时刻。长按并【复制】这段话，打开【搜狐新闻】直接加我为好友。");
        result.put("mobile", "我是%s，去应用市场下载【搜狐新闻】App，体验新版搜狐时刻。长按并【复制】这段话，打开【搜狐新闻】直接加我为好友。");
        for (String key : result.keySet()) {
            System.out.println(result.getString(key));
            System.out.println("");
        }
        System.out.println(result);
    }

    public static JSONObject getjs() {
        JSONObject result = new JSONObject();
        result.put("aaa", "aaaaa");
        return result;
    }

    static class People {
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

    @Test
    public void info3(){
        Set<Long> longSet = new HashSet<>();
        longSet.add(6228812705470787697l);
        longSet.add(6228812705470787696l);
        longSet.add(6228812705470787695l);
        String data = "{\"6228812705470787697\": \"1\",\"6228812705470787696\": \"1\",\"6228812705470787695\": \"1\"}";
        JSONObject jsonObject = JSONObject.parseObject(data);
        longSet.forEach(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                System.out.println(aLong+" "+jsonObject.getString(String.valueOf(aLong)));
            }
        });
        System.out.println(JSONObject.parseObject(data));
    }
}
