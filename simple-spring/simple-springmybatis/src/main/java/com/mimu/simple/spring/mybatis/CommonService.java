package com.mimu.simple.spring.mybatis;

import com.mimu.simple.spring.mybatis.mapper.UserMapper;
import com.mimu.simple.spring.mybatis.model.UserData;
import com.mimu.simple.spring.mybatis.model.UserDataResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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


    /**
     * 只有 使用 @Transactional 标注的方法 抛出异常 事务才可以回滚
     * 事物回滚
     * 只有遇到 RuntimeException 或 Error 时
     * spring 事务才会执行 rollback 操作
     *
     * @param
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {RuntimeException.class, Exception.class})
    public boolean insertMultiRecordWithTransaction(UserData data, UserData userData) {
        insert(data);
        insert(userData);
        return true;
    }

    private boolean insert(UserData data) {
        int i = userMapper.insertUser(data);
        if (i > 0) {
            return true;
        } else {
            throw new RuntimeException();
        }

    }

    public UserDataResultMap listUserTermInfoLazy(UserData data){
        return userMapper.listUserTermInfoLazy(data);
    }
}
