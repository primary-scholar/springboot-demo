package com.mimu.simple.mybatis.mapper;

import com.mimu.simple.mybatis.model.UserData;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

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
    public void updataUser() {
    }
}