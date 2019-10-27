package com.mimu.simple.mybatis.dao;

import com.mimu.simple.mybatis.model.UserData;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * author: mimu
 * date: 2019/10/26
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public int insertUser(UserData data) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int insert = sqlSession.insert("user.insertUser", data);
        sqlSession.commit();
        sqlSession.close();
        return insert;
    }

    @Override
    public UserData insertUserReturnPrimaryId(UserData data) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.insert("user.insertUserReturnPrimaryId", data);
        sqlSession.commit();
        sqlSession.close();
        return data;
    }

    @Override
    public UserData findUserById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserData userData = sqlSession.selectOne("user.findUserById", id);
        sqlSession.close();
        return userData;
    }

    @Override
    public List<UserData> listUserByName(String name) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<UserData> userData = sqlSession.selectList("user.listUserByName", name);
        sqlSession.close();
        return userData;
    }

    @Override
    public int deleteUserById(int id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int recode = sqlSession.delete("user.deleteUser", id);
        sqlSession.commit();
        sqlSession.close();
        return recode;
    }

    @Override
    public int updateUser(UserData data) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int recode = sqlSession.update("user.updateUser", data);
        sqlSession.commit();
        sqlSession.close();
        return recode;
    }
}
