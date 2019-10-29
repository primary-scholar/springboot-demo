package com.mimu.simple.spring.mybatis.mapper;

import com.mimu.simple.spring.mybatis.model.UserData;
import com.mimu.simple.spring.mybatis.model.UserDataResultMap;
import com.mimu.simple.spring.mybatis.model.UserDataVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: mimu
 * date: 2019/10/28
 */

/**
 * here @Repository 是否添加都可以，不添加时 idea 进行 CommonService @Autowired 注入时 会提示错误，
 * 实际上 没有问题
 */
@Mapper
@Repository
public interface UserMapper {
    UserData findUserById(int id);

    List<UserData> listUserByName(String name);

    int insertUser(UserData data);

    int insertUserReturnPrimaryId(UserData data);

    int deleteUser(int id);

    int updataUser(UserData data);

    List<UserDataVo> listUserMapInfo(UserData data);

    UserDataResultMap listUserTermInfoLazy(UserData data);

}
