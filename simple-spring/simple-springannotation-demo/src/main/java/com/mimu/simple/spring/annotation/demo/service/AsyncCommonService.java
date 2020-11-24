package com.mimu.simple.spring.annotation.demo.service;

import com.mimu.simple.spring.annotation.demo.model.PersonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class AsyncCommonService {
    private static Logger logger = LoggerFactory.getLogger(AsyncCommonService.class);

    @Async
    public PersonData getPersonDataById(int id){
        PersonData async = PersonData.builder().nickName("async").pid(id).build();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("info {}",async);
        return async;
    }

    @Async
    public PersonData getPersonDataByName(String name){
        PersonData async = PersonData.builder().nickName(name).pid(111).build();
        try {
            Thread.sleep(2010);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("info {}",async);
        return async;
    }
}
