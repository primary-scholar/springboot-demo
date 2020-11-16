package com.mimu.simple.spring.mybatis.generator;

import com.mimu.simple.spring.mybatis.generator.config.ApplicationConfig;
import com.mimu.simple.spring.mybatis.generator.model.school.CourseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * author: mimu
 * date: 2019/10/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    @Test
    public void countByExample() {
        long l = commonService.countByExample(10001);
        System.out.println(l);
    }

    @Test
    public void complexFind() {
        List<CourseInfo> termInfos = commonService.find();
        termInfos.forEach(termInfo -> System.out.println(termInfo));
    }
}