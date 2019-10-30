package com.mimu.simple.springboot.mybatis.generator.mapper;

import com.mimu.simple.springboot.mybatis.generator.model.TermInfo;
import org.springframework.stereotype.Component;

/**
 * here @Component 是否添加都可以，不添加时 idea 进行 CommonService @Autowired 注入时 会提示错误，
 * 实际上 没有问题
 */
@Component
public interface TermInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TermInfo record);

    int insertSelective(TermInfo record);

    TermInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TermInfo record);

    int updateByPrimaryKey(TermInfo record);
}