package com.mimu.simple.mybatis.mapper;

import com.mimu.simple.mybatis.model.UserData;

import java.util.List;

/**
 * author: mimu
 * date: 2019/10/27
 */
public interface UserMapper {

    UserData findUserById(int id);

    List<UserData> listUserByName(String name);

    int insertUser(UserData data);

    int deleteUser(int id);

    int updataUser(UserData data);
}
