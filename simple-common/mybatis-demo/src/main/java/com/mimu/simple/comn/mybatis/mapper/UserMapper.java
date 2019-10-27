package com.mimu.simple.comn.mybatis.mapper;


import com.mimu.simple.comn.mybatis.model.UserData;
import com.mimu.simple.comn.mybatis.model.UserDataVo;

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

    List<UserDataVo> listUserMapInfo(UserData data);
}
