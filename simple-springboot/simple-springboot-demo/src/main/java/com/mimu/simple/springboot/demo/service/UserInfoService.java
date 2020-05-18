package com.mimu.simple.springboot.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.mimu.simple.springboot.demo.config.customizer.cache.RedisTTlConstant;
import com.mimu.simple.springboot.demo.dao.UserInfoRepository;
import com.mimu.simple.springboot.demo.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 author: mimu
 date: 2020/4/27
 */

/**
 * spring cache 必须使用代理对象进行访问才会生效(内部调用不生效)
 */
@Slf4j
@Service
public class UserInfoService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo getUserInfo(int pid) {
        String key = "mo::user_" + pid;
        String s = redisTemplate.opsForValue().get(key);
        if (StringUtils.isNotEmpty(s)) {
            return JSONObject.parseObject(s, UserInfo.class);
        }
        UserInfo userInfo = userInfoRepository.getUserInfo(pid);
        if (userInfo != null) {
            String s1 = JSONObject.toJSONString(userInfo);
            redisTemplate.opsForValue().set(key, s1, Duration.ofMillis(60000));
        }
        log.info("{}", userInfo);
        return userInfo;
    }

    /**
     * 这里 Cacheable 执行逻辑:
     * 和 {@link UserInfoService #getUserInfo(int pid) }
     *  逻辑 一致 key 的拼接也是 一致的
     *  只是 没有 设置 有效期
     * @param pid
     * @return
     */
    @Cacheable(cacheNames = RedisTTlConstant.minute_1_info, key = "'user_'+#p0", unless = "#result==null")
    public UserInfo getUserInfoCacheableEquivalent(int pid) {
        return userInfoRepository.getUserInfo(pid);
    }

    /**
     * CachePut 的执行逻辑
     * 是 执行方法，并更新缓存
     * 更新的 缓存中的值 是 方法的 返回值 这点 需 注意!!!!
     * @param userInfo
     * @return
     */
    @CachePut(cacheNames = RedisTTlConstant.minute_1_info, key = "'user_'+#userInfo.personId")
    public boolean updateUserInfo(UserInfo userInfo) {
        return userInfoRepository.updateUserInfo(userInfo);
    }


    @CacheEvict(cacheNames = RedisTTlConstant.minute_1_info, key = "'user_'+#userInfo.personId")
    public boolean updateUser(UserInfo userInfo) {
        return userInfoRepository.updateUserInfo(userInfo);
    }

    public boolean deleteUserInfo(int pid) {
        boolean b = userInfoRepository.deleteUserInfo(pid);
        if (b) {
            String key = "mo::user_" + pid;
            redisTemplate.delete(key);
        }
        return b;
    }

    /**
     * 这里 CacheEvict 执行逻辑:
     * 和 {@link UserInfoService #deleteUserInfo(int pid) 逻辑 一致 key 的拼接也是 一致的 }
     * @param personId
     * @return
     */
    @CacheEvict(cacheNames = RedisTTlConstant.minute_1_info, key = "'user_'+#p0")
    public boolean deleteUserInfoCacheEvitEquivalent(int personId) {
        return userInfoRepository.deleteUserInfo(personId);
    }
}
