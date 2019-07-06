package com.mimu.simple.sa;

import com.mimu.simple.sa.config.ApplicationConfig;
import com.mimu.simple.sa.model.People;
import com.mimu.simple.sa.model.Term;
import com.mimu.simple.sa.service.CommonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class InnerServiceTest {

    protected CommonService commonService;

    @Autowired
    public void setCommonService(CommonService commonService) {
        this.commonService = commonService;
    }

    @Test
    public void saveInfo() {
        People people = new People();
        people.setPid(2);
        people.setName("nickname2");
        Term term = new Term();
        term.setPid(2);
        term.setTid(2);
        Assert.assertTrue(commonService.saveInfo(people, term));
    }


    @Test
    public void updateInfo() {
        People people = new People();
        people.setPid(2);
        people.setName("nickname2");
        Term term = new Term();
        term.setPid(1);
        term.setTid(1);
        commonService.updateInfo(people,term);
        //Assert.assertTrue(commonService.updateInfo(people, term));
    }


}