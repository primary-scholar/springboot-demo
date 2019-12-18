package com.mimu.simple.springboot.mybatis.multipledb.mapper;

import com.mimu.simple.springboot.mybatis.multipledb.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * author: mimu
 * date: 2019/8/1
 */

/**
 * here @Repository 是否添加都可以，不添加时 idea 进行 CommonService @Autowired 注入时 会提示错误，
 * 实际上 没有问题
 */
@Repository
public interface UserDataMapper {

    @Select("select * from user_info where person_id=#{id}")
    @Results(value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "person_id", property = "personId", jdbcType = JdbcType.INTEGER),
            @Result(column = "person_name", property = "personName", jdbcType = JdbcType.VARCHAR)
    })
    UserInfo getUserInfoById(@Param(value = "id") long personId);
}
