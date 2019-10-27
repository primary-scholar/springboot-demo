package com.mimu.simple.mybatis.dao;

import com.mimu.simple.mybatis.model.UserData;

import java.util.List;

/**
 * author: mimu
 * date: 2019/10/26
 */
public interface UserDao {

    public UserData insertUserReturnPrimaryId(UserData data);

    public int insertUser(UserData data);

    public UserData findUserById(int id);

    public List<UserData> listUserByName(String name);

    public int deleteUserById(int id);

    public int updateUser(UserData data);
}
