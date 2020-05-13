package com.mimu.simple.spring.annotation.demo.service;

import com.mimu.simple.spring.annotation.demo.config.ApplicationConfig;
import com.mimu.simple.spring.annotation.demo.model.PersonData;
import com.mimu.simple.spring.annotation.demo.model.TermData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class InnerServiceTest {

    protected CommonService commonService;

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }


    @Test
    public void getInfoWithCache(){
        PersonData peopleWithCache = commonService.getPeopleWithCache(1);
        System.out.println(peopleWithCache);
    }

    @Test
    public void saveInfo() {
        PersonData personData = new PersonData();
        personData.setPid(2);
        personData.setNickName("nickname2");
        TermData termData = new TermData();
        termData.setPid(2);
        termData.setTid(2);
        Assert.assertTrue(commonService.saveInfo(personData, termData));
    }


    @Test
    public void updateInfo() {
        PersonData personData = new PersonData();
        personData.setPid(20);
        personData.setNickName("nickname1");
        TermData termData = new TermData();
        termData.setPid(2);
        termData.setTid(6);
        commonService.updateInfo(personData, termData);
        Assert.assertTrue(commonService.updateInfo(personData, termData));
    }

    @Test
    public void list() {
        Set<String> termIdSet = new HashSet<>();
        termIdSet.add(String.valueOf(1));
        termIdSet.add(String.valueOf(2));
        List<TermData> termData = commonService.listInfo(termIdSet);
        for (TermData termDatum : termData) {
            System.out.println(termDatum);
        }
    }


    @Test
    public void info() {
        while (true) {
            PersonData personData = commonService.getPeople(1);
            System.out.println(personData);
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void logger() {
        System.out.println(commonService.getPeople(1));
    }


}