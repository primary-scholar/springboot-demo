package com.mimu.simple.mybatis;


import com.mimu.simple.mybatis.model.UserData;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * author: mimu
 * date: 2019/10/26
 */
public class SimpleMybatisDemo {

    public SqlSession getSqlSession() throws IOException {
        String file = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(file);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }

    public void findInfo() throws IOException {
        SqlSession sqlSession = getSqlSession();
        UserData userData = sqlSession.selectOne("user.findUserById", 1);
        System.out.println(userData);
        sqlSession.close();
    }

    public void listInfo() throws IOException {
        SqlSession sqlSession = getSqlSession();
        List<UserData> userData = sqlSession.selectList("user.listUserByName", "name");
        System.out.println(userData);
        sqlSession.close();
    }

    public void insertUser() throws IOException {
        int id = 8;
        SqlSession sqlSession = getSqlSession();
        UserData userData = new UserData();
        userData.setPerson_id(id);
        userData.setPserson_name("first");
        sqlSession.insert("user.insertUser", userData);
        sqlSession.commit();
        System.out.println(userData);
        UserData userData1 = new UserData();
        userData1.setPerson_id(id + 1);
        userData1.setPserson_name("second");
        /**
         * 该语句返回的 userdata中的id为数据库中主见返回的 ID；
         */
        sqlSession.insert("user.insertUserReturnPrimaryId", userData1);
        sqlSession.commit();
        System.out.println(userData1);
        sqlSession.close();
    }

    public void deleteUser() throws IOException {
        int id = 8;
        SqlSession sqlSession = getSqlSession();
        int delete = sqlSession.delete("user.deleteUser", id);
        sqlSession.commit();
        System.out.println(delete);
    }

    public void updateUser() throws IOException {
        int id = 9;
        UserData userData = new UserData();
        userData.setId(id);
        userData.setPserson_name("updataName");
        userData.setPerson_id(1);
        SqlSession sqlSession = getSqlSession();
        int update = sqlSession.update("user.updateUser", userData);
        sqlSession.commit();
        System.out.println(update);
    }


    public static void main(String[] args) throws IOException {
        SimpleMybatisDemo demo = new SimpleMybatisDemo();
        demo.updateUser();

    }
}
