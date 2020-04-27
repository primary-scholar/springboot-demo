package com.mimu.simple.springboot.demo.service;

import com.mimu.simple.springboot.demo.request.UserInfoRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setPid(1);
        userInfoRequest.setCid(2);
        while (true) {
            userInfoService.getUserInfo(userInfoRequest);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}