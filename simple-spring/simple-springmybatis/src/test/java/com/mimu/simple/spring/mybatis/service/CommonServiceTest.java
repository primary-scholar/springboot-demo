package com.mimu.simple.spring.mybatis.service;

import com.mimu.simple.spring.mybatis.CommonService;
import com.mimu.simple.spring.mybatis.config.ApplicationConfig;
import com.mimu.simple.spring.mybatis.model.TermData;
import com.mimu.simple.spring.mybatis.model.UserData;
import com.mimu.simple.spring.mybatis.model.UserDataResultMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * author: mimu
 * date: 2019/10/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    @Test
    public void finUserById() {
        System.out.println(commonService.finUserById(1));
    }

    @Test
    public void insertUser() {
        UserData data = new UserData();
        data.setPerson_id(1);
        data.setPerson_name("mapper insert");
        UserData data1 = commonService.insertUserDataWithPrimaryId(data);
        System.out.println(data1);
    }

    @Test
    public void insertWithTransaction() {
        UserData data = new UserData();
        data.setPerson_name("abc");
        data.setPerson_id(2);
        System.out.println(commonService.insertMultiRecordWithTransaction(data, data));
    }

    /**
     * 使用 该测试(listUserTermInfoLazyOne) 和(listUserTermInfoLazy)  可验证 延迟加载
     */
    @Test
    public void listUserTermInfoLazyOne() {
        UserData userData = new UserData();
        userData.setPerson_id(2);
        userData.setPerson_name("hah");
        UserDataResultMap result = commonService.listUserTermInfoLazy(userData);
    }

    @Test
    public void listUserTermInfoLazy() {
        UserData userData = new UserData();
        userData.setPerson_id(2);
        userData.setPerson_name("hah");
        UserDataResultMap result = commonService.listUserTermInfoLazy(userData);
        List<TermData> termDataList = result.getTermDataList();
        for (TermData termData : termDataList) {
            System.out.println(termData);
        }
    }
}