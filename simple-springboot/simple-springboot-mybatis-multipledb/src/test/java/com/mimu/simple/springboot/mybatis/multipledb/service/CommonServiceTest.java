package com.mimu.simple.springboot.mybatis.multipledb.service;

import com.mimu.simple.springboot.mybatis.multipledb.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 author: mimu
 date: 2019/12/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class, MybatisAutoConfiguration.class})
@Import(value = {ApplicationConfig.class})
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    @Test
    public void getInfo() {
        System.out.println(commonService.getUserData(8));
        System.out.println(commonService.getTermData(0));
    }

    @Test
    public void getInfo1() {
        System.out.println(commonService.getTermData2(1));
        System.out.println(commonService.getTermData1(1));
    }

}