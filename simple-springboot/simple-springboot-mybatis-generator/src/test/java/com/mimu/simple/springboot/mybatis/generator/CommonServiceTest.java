package com.mimu.simple.springboot.mybatis.generator;

import com.mimu.simple.springboot.mybatis.generator.model.UserInfo;
import com.mimu.simple.springboot.mybatis.generator.service.CommonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * author: mimu
 * date: 2019/10/30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    @Test
    public void getUserInfo() {
        System.out.println(commonService.getUserInfo(1));
    }

    @Test
    public void getTermInfo() {
        System.out.println(commonService.getTermInfo(10));
    }

    /**
     * 全量 field 插入，对于自增 id 设不设置 数值 都是可以的
     */
    @Test
    public void insertUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(0);
        userInfo.setPersonId(10);
        userInfo.setPersonName("nickname10");
        System.out.println(commonService.insertUserInfo(userInfo));
    }

    @Test
    public void insertSelective() {
        UserInfo userInfo = new UserInfo();
        //userInfo.setId(0);
        userInfo.setPersonId(11);
        //userInfo.setPersonName("nickname10");
        System.out.println(commonService.insertUserSelective(userInfo));
        //return userInfoMapper.insertSelective(userInfo);
    }

    @Test
    public void deleteUserInfo() {
        System.out.println(commonService.deleteUserInfo(16));
    }
}