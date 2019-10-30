package com.mimu.simple.springboot.mybatis.generator.mapper;

import com.mimu.simple.springboot.mybatis.generator.model.UserInfo;
import org.springframework.stereotype.Component;

/**
 * here @Component 是否添加都可以，不添加时 idea 进行 CommonService @Autowired 注入时 会提示错误，
 * 实际上 没有问题
 */
@Component
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}