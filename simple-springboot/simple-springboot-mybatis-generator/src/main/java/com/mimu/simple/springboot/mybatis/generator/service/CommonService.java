package com.mimu.simple.springboot.mybatis.generator.service;

import com.mimu.simple.springboot.mybatis.generator.mapper.TermInfoMapper;
import com.mimu.simple.springboot.mybatis.generator.mapper.UserInfoMapper;
import com.mimu.simple.springboot.mybatis.generator.model.TermInfo;
import com.mimu.simple.springboot.mybatis.generator.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: mimu
 * date: 2019/10/30
 */

@Service
public class CommonService {

    private UserInfoMapper userInfoMapper;
    private TermInfoMapper termInfoMapper;

    @Autowired
    public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Autowired
    public void setTermInfoMapper(TermInfoMapper termInfoMapper) {
        this.termInfoMapper = termInfoMapper;
    }

    public UserInfo getUserInfo(int key) {
        return userInfoMapper.selectByPrimaryKey(key);
    }

    public TermInfo getTermInfo(int key) {
        return termInfoMapper.selectByPrimaryKey(key);
    }

    public int insertUserInfo(UserInfo userInfo) {
        return userInfoMapper.insert(userInfo);
    }

    public int insertUserSelective(UserInfo userInfo) {
        return userInfoMapper.insertSelective(userInfo);
    }

    public int deleteUserInfo(int id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }
}
