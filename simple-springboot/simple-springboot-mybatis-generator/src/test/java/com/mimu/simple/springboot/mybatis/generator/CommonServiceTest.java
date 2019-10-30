package com.mimu.simple.springboot.mybatis.generator;

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
        System.out.println(commonService.getTermInfo(1));
    }
}