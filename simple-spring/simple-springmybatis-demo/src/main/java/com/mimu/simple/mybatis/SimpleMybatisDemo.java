package com.mimu.simple.mybatis;


import com.mimu.simple.mybatis.model.UserData;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * author: mimu
 * date: 2019/10/26
 */
public class SimpleMybatisDemo {

    public static void main(String[] args) throws IOException {
        String file = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(file);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserData userData = sqlSession.selectOne("user.findUserById",1);
        System.out.println(userData);
        sqlSession.close();
    }
}
