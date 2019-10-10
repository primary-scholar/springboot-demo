package com.mimu.simple.springboot.ssbd1.service;

import com.alibaba.fastjson.JSONObject;
import com.mimu.simple.httpclient.SimpleHttpClient;
import com.mimu.simple.springboot.ssbd1.model.UserData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * author: mimu
 * date: 2019/6/29
 */
@Service
public class CommonService {
    private String url = "http://localhost:9093/sb3/info.go?pid=%s";

    public UserData getUserData(long pid) {
        String result = SimpleHttpClient.get(String.format(url, pid));
        if (StringUtils.isNotEmpty(result)) {
            UserData data = JSONObject.parseObject(result, UserData.class);
            data.setPid(data.getPid() + pid * 10);
            return data;
        }
        return new UserData();
    }
}
