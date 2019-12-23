package com.mimu.simple.springboot.mybatis.multipledb.service;

import com.mimu.simple.springboot.mybatis.multipledb.config.ApplicationConfig;
import com.mimu.simple.springboot.mybatis.multipledb.model.TermInfo;
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

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public void getInfo1() throws InterruptedException {
        System.out.println(commonService.getTermData2(1));
        System.out.println(commonService.getTermData1(1));
        /*System.out.println(commonService.getTermData2(1));
        System.out.println(commonService.getTermData1(1));*/
    }

    @Test
    public void getInfo2() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 1; i <= 20; i++) {
            int finalI = i;
            executorService.submit(() -> {
                for (int loop = 0; loop < finalI * 1000; loop++) {
                    if (finalI % 2 == 0) {
                        TermInfo termData2 = commonService.getTermData2(1);
                        assert termData2.getId() == 1;
                    } else {
                        TermInfo termData1 = commonService.getTermData1(1);
                        assert termData1.getId() == 0;
                    }

                }
            });
        }
        /*new Thread(() -> {
            while (true) {
                TermInfo termData2 = commonService.getTermData2(1);
                assert termData2.getId() == 1;
            }
        }).start();
        new Thread(() -> {
            while (true) {
                TermInfo termData1 = commonService.getTermData1(1);
                assert termData1.getId() == 0;
            }
        }).start();*/
        System.in.read();
        executorService.shutdown();
    }

}