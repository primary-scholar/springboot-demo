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
}
