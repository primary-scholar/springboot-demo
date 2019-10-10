package com.mimu.simple.springboot.sdc.mapper.term;

import com.mimu.simple.springboot.sdc.model.TermData;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * author: mimu
 * date: 2019/7/30
 */
@Mapper
@Repository
public interface TermDataMapper {

    @Select("select * from term_info where person_id=${id}")
    @Results(value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "term_id", property = "termId", jdbcType = JdbcType.INTEGER),
            @Result(column = "person_id", property = "pId", jdbcType = JdbcType.BIGINT)
    })
    TermData getTermDataById(@Param(value = "id") long personId);
}
