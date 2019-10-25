package com.mimu.simple.springboot.sdc.mapper.user;

import com.mimu.simple.springboot.sdc.model.UserData;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * author: mimu
 * date: 2019/8/1
 */
@Mapper
@Repository
public interface UserDataMapper {

    @Select("select * from user_info where person_id=#{id}")
    @Results(value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "person_id", property = "pid", jdbcType = JdbcType.INTEGER),
            @Result(column = "person_name", property = "name", jdbcType = JdbcType.VARCHAR)
    })
    UserData getUserDataById(@Param(value = "id") long personId);
}
