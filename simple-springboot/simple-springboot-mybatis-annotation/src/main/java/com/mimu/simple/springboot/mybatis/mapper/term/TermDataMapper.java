package com.mimu.simple.springboot.mybatis.mapper.term;

import com.mimu.simple.springboot.mybatis.model.TermInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

/**
 * author: mimu
 * date: 2019/7/30
 */

/**
 * here @Repository 是否添加都可以，不添加时 idea 进行 CommonService @Autowired 注入时 会提示错误，
 * 实际上 没有问题
 */
@Repository
public interface TermDataMapper {

    @Select("select * from term_info where person_id=#{id}")
    @Results(value = {
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER),
            @Result(column = "term_id", property = "termId", jdbcType = JdbcType.INTEGER),
            @Result(column = "person_id", property = "personId", jdbcType = JdbcType.BIGINT)
    })
    TermInfo getTermInfoById(@Param(value = "id") long personId);
}
