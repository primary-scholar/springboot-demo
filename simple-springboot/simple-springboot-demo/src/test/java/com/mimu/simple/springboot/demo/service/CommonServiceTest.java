package com.mimu.simple.springboot.demo.service;

import com.mimu.simple.springboot.demo.request.UserInfoRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * author: mimu
 * date: 2019/10/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    @Test
    public void getUserInfo() {
        System.out.println(commonService.getUserInfo(10));
    }

    @Test
    public void getUserInfo1(){
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setPid(1);
        userInfoRequest.setCid(2);
        while (true){
            System.out.println(commonService.getUserInfo(userInfoRequest));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getTermInfo() {
        System.out.println(commonService.getTermInfo(1));
    }
}