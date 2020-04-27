package com.mimu.simple.springboot.demo.service;

import com.mimu.simple.springboot.demo.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 author: mimu
 date: 2020/4/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoServiceTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void getUserInfo() {
        UserInfo userInfo1 = userInfoService.getUserInfoCacheableEquivalent(1);
        System.out.println(userInfo1);
    }

    @Test
    public void updateInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setPersonId(1);
        userInfo.setPersonName("123");
        userInfoService.updateUser(userInfo);
    }
}