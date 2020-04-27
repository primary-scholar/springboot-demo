package com.mimu.simple.springboot.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.mimu.simple.springboot.demo.dao.TermInfoRepository;
import com.mimu.simple.springboot.demo.dao.UserInfoRepository;
import com.mimu.simple.springboot.demo.model.UserInfo;
import com.mimu.simple.springboot.demo.request.UserInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 author: mimu
 date: 2020/4/27
 */
@Slf4j
@Service
public class UserInfoService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo getUserInfo(UserInfoRequest request) {
        String key = "key_" + request.getPid() + " " + request.getCid();
        String s = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(s)) {
            return JSONObject.parseObject(s, UserInfo.class);
        }
        UserInfo userInfo = userInfoRepository.getUserInfo(request.getPid());
        if (userInfo != null) {
            String s1 = JSONObject.toJSONString(userInfo);
            redisTemplate.opsForValue().set(key, s1, Duration.ofMillis(2000));
        }
        log.info("{}", userInfo);
        return userInfo;
    }
}
