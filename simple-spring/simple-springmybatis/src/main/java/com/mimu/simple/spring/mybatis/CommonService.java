package com.mimu.simple.spring.mybatis;

import com.mimu.simple.spring.mybatis.mapper.UserMapper;
import com.mimu.simple.spring.mybatis.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: mimu
 * date: 2019/10/28
 */
@Service
public class CommonService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public UserData finUserById(int id) {
        return userMapper.findUserById(id);
    }

    public int insertUserData(UserData data) {
        return userMapper.insertUser(data);
    }

    public UserData insertUserDataWithPrimaryId(UserData data) {
        userMapper.insertUserReturnPrimaryId(data);
        return data;
    }
}
