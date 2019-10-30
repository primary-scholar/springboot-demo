package com.mimu.simple.springboot.mybatis.service;

import com.mimu.simple.springboot.mybatis.mapper.term.TermDataMapper;
import com.mimu.simple.springboot.mybatis.mapper.user.UserDataMapper;
import com.mimu.simple.springboot.mybatis.model.TermInfo;
import com.mimu.simple.springboot.mybatis.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: mimu
 * date: 2019/7/29
 */
@Service
public class CommonService {

    private TermDataMapper termDataMapper;
    private UserDataMapper userDataMapper;

    @Autowired
    public void setTermDataMapper(TermDataMapper termDataMapper) {
        this.termDataMapper = termDataMapper;
    }

    @Autowired
    private void setUserDataMapper(UserDataMapper userDataMapper) {
        this.userDataMapper = userDataMapper;
    }

    public TermInfo getTermData(long pid) {
        TermInfo data = termDataMapper.getTermInfoById(pid);
        return data != null ? data : TermInfo.builder().id(0).personId(0).termId(0).build();
    }

    public UserInfo getUserData(long pid) {
        UserInfo data = userDataMapper.getUserInfoById(pid);
        return data != null ? data : UserInfo.builder().id(0).personId(0).personName("").build();
    }
}
