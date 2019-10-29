package com.mimu.simple.comn.mybatis.mapper;


import com.mimu.simple.comn.mybatis.model.TermData;
import com.mimu.simple.comn.mybatis.model.UserData;
import com.mimu.simple.comn.mybatis.model.UserDataResultMap;
import com.mimu.simple.comn.mybatis.model.UserDataVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * author: mimu
 * date: 2019/10/27
 */
public class UserMapperTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory() throws IOException {
        String file = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(file);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /**
         * mybatis 自动生成 mapper 的代理对象
         */
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserData userById = mapper.findUserById(1);
        System.out.println(userById);
    }

    @Test
    public void listUserByName() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /**
         * mybatis 自动生成 mapper 的代理对象
         */
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<UserData> name = mapper.listUserByName("name");
        System.out.println(name);
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void deleteUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /**
         * mybatis 自动生成 mapper 的代理对象
         */
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.deleteUser(9);
        System.out.println(i);
    }

    @Test
    public void listUserMapInfo() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserData userData = new UserData();
        userData.setPerson_id(2);
        userData.setPerson_name("hah");
        List<UserDataVo> userDataVos = mapper.listUserMapInfo(userData);
        System.out.println(userDataVos);
    }

    @Test
    public void listUserTermInfo() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserData userData = new UserData();
        userData.setPerson_id(2);
        userData.setPerson_name("hah");
        UserDataResultMap result = mapper.listUserTermInfo(userData);
        System.out.println(result);
    }


    @Test
    public void listUserTermInfoLazyOne() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserData userData = new UserData();
        userData.setPerson_id(2);
        userData.setPerson_name("hah");
        UserDataResultMap result = mapper.listUserTermInfoLazy(userData);
    }
    @Test
    public void listUserTermInfoLazy() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserData userData = new UserData();
        userData.setPerson_id(2);
        userData.setPerson_name("hah");
        UserDataResultMap result = mapper.listUserTermInfoLazy(userData);
        List<TermData> termDataList = result.getTermDataList();
        for (TermData termData : termDataList) {
            System.out.println(termData);
        }
    }
}