package com.mimu.simple.springboot.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.mimu.simple.springboot.demo.dao.TermInfoRepository;
import com.mimu.simple.springboot.demo.dao.UserInfoRepository;
import com.mimu.simple.springboot.demo.model.TermInfo;
import com.mimu.simple.springboot.demo.model.UserInfo;
import com.mimu.simple.springboot.demo.request.UserInfoRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

/**
 * author: mimu
 * date: 2019/7/5
 */
@Service
public class CommonService {
    private static final Logger logger = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    private TermInfoRepository termInfoRepository;
    private UserInfoRepository userInfoRepository;

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Autowired
    public void setTermInfoRepository(TermInfoRepository termInfoRepository) {
        this.termInfoRepository = termInfoRepository;
    }

    public UserInfo getUserInfo(int pid) {
        return userInfoRepository.getUserInfo(pid);
    }

    public TermInfo getTermInfo(int pid) {
        return termInfoRepository.getTermInfo(pid);
    }

    @Transactional(transactionManager = "userTxManager", rollbackFor = RuntimeException.class)
    public boolean updateInfo(int pid, String nickName, int termId) {
        return addTermInfo1(pid, termId) && addUserInfo(pid, nickName);
    }

    public UserInfo getUserInfo(UserInfoRequest request) {
        String key = "key_" + request.getPid() + " " + request.getCid();
        String s = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(s)) {
            System.out.println("get from redis");
            return JSONObject.parseObject(s, UserInfo.class);
        }
        UserInfo userInfo = userInfoRepository.getUserInfo(request.getPid());
        if (userInfo != null) {
            String s1 = JSONObject.toJSONString(userInfo);
            redisTemplate.opsForValue().set(key, s1, Duration.ofMillis(1000));
        }
        logger.info("{}", userInfo);
        return userInfo;
    }

    private boolean addTermInfo(int pid, int termId) {
        if (!termInfoRepository.addTermInfo(pid, termId)) {
            throw new RuntimeException("insert termData error");
        } else {
            return true;
        }
    }

    private boolean addUserInfo(int pid, String nickName) {
        if (!userInfoRepository.addUserInfo(pid, nickName)) {
            throw new RuntimeException("insert termData error");
        } else {
            return true;
        }
    }

    private boolean addTermInfo1(long pid, int termId) {
        if (!userInfoRepository.addTermInfo(pid, termId)) {
            throw new RuntimeException("insert termData error");
        } else {
            return true;
        }
    }


}
