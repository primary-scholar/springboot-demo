package com.mimu.simple.springboot.sdc;


import com.mimu.simple.springboot.sdc.config.TermDataSourceConfig;
import com.mimu.simple.springboot.sdc.config.UserDataSourceConfig;
import com.mimu.simple.springboot.sdc.mapper.term.TermDataMapper;
import com.mimu.simple.springboot.sdc.mapper.user.UserDataMapper;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class, MybatisAutoConfiguration.class})
@Import(value = {TermDataSourceConfig.class, UserDataSourceConfig.class})
public class SBMAApplicationTest {

    private TermDataMapper termDataMapper;
    private UserDataMapper userDataMapper;

    @Autowired
    public void setTermDataMapper(TermDataMapper termDataMapper) {
        this.termDataMapper = termDataMapper;
    }

    @Autowired
    public void setUserDataMapper(UserDataMapper userDataMapper) {
        this.userDataMapper = userDataMapper;
    }

    @Test
    public void info() {
        System.out.println(termDataMapper.getTermDataById(1));
    }

    @Test
    public void info1(){
        System.out.println(userDataMapper.getUserDataById(1));
    }
}
