package com.mimu.simple.spring.mybatis.mapper;

import com.mimu.simple.spring.mybatis.model.UserData;
import com.mimu.simple.spring.mybatis.model.UserDataResultMap;
import com.mimu.simple.spring.mybatis.model.UserDataVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: mimu
 * date: 2019/10/28
 */

/**
 * here @Repository 是否添加都可以，不添加时 idea 进行 CommonService @Autowired 注入时 会提示错误，
 * 实际上 没有问题, 此处 @mapper注解 也可不加，在 @MapperScan 中引入的 MapperScannerRegistrar 注册器
 * 会 扫描到指定包 下的所有mapper 并把 扫描到的 mapper
 * 解析成 beanDefinition,同时指定该 definition 中的 beanClass 为 MapperFactoryBean<T>(one of  FactoryBean) 这样 在 @autowire
 * 解析的过程中 借可以获取到 该依赖的 具体实例 factoryBean.getObject() 对象,具体对象为 MapperProxy的代理类
 */
//@Mapper
@Repository
public interface UserMapper {
    UserData findUserById(int id);

    List<UserData> listUserByName(String name);

    int insertUser(UserData data);

    int insertUserReturnPrimaryId(UserData data);

    int deleteUser(int id);

    int updataUser(UserData data);

    List<UserDataVo> listUserMapInfo(UserData data);

    UserDataResultMap listUserTermInfoLazy(UserData data);

}
