package com.mimu.simple.spring.annotation.demo.service;

import com.mimu.simple.spring.annotation.demo.config.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class AsyncCommonServiceTest {
    private static Logger logger = LoggerFactory.getLogger(AsyncCommonServiceTest.class);

    @Autowired
    private AsyncCommonService asyncCommonService;

    @Test
    public void info() throws InterruptedException {
        long start = System.currentTimeMillis();
        asyncCommonService.getPersonDataById(111);
        asyncCommonService.getPersonDataByName("111");
        Thread.sleep(5000);
        logger.info("cost {}", System.currentTimeMillis() - start);
    }

}