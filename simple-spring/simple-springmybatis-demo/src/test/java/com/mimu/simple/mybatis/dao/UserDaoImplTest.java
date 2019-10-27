package com.mimu.simple.mybatis.dao;

import com.mimu.simple.mybatis.model.UserData;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * author: mimu
 * date: 2019/10/26
 */
public class UserDaoImplTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory() throws IOException {
        String file = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(file);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void insertUser(){
        int id = 8;
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setSqlSessionFactory(sqlSessionFactory);
        UserData userData = new UserData();
        userData.setPerson_id(id);
        userData.setPserson_name("first");
        userDao.insertUser(userData);
        System.out.println(userData);
        UserData userData1 = new UserData();
        userData1.setPerson_id(id + 1);
        userData1.setPserson_name("second");

        userDao.insertUserReturnPrimaryId(userData1);
        System.out.println(userData1);
    }

    @Test
    public void findUserById() {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setSqlSessionFactory(sqlSessionFactory);
        System.out.println(userDao.findUserById(1));
    }

    @Test
    public void listUserByName() {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setSqlSessionFactory(sqlSessionFactory);
        System.out.println(userDao.listUserByName("name"));
    }

    @Test
    public void deleteUserById() {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setSqlSessionFactory(sqlSessionFactory);
        System.out.println(userDao.deleteUserById(6));
    }

}