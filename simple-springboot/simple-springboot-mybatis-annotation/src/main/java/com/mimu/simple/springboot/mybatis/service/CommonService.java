package com.mimu.simple.springboot.mybatis.service;

import com.mimu.simple.springboot.mybatis.mapper.term.TermDataMapper;
import com.mimu.simple.springboot.mybatis.mapper.user.UserDataMapper;
import com.mimu.simple.springboot.mybatis.model.TermData;
import com.mimu.simple.springboot.mybatis.model.UserData;
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

    public TermData getTermData(long pid) {
        TermData data = termDataMapper.getTermDataById(pid);
        return data != null ? data : TermData.builder().id(0).pId(0).termId(0).build();
    }

    public UserData getUserData(long pid) {
        UserData userData = userDataMapper.getUserDataById(pid);
        return userData != null ? userData : UserData.builder().id(0).pid(0).name("").build();
    }
}
