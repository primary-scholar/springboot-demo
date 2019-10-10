package com.mimu.simple.springboot.sdc.config;


import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * author: mimu
 * date: 2019/8/1
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.mimu.simple.springboot.sdc.mapper.term", sqlSessionFactoryRef = "termSqlSessionFactory")
public class TermDataSourceConfig {

    /**
     * generate datasource
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "term.datasource")
    public DataSource termDataSource() {
        log.info("init termDataSource");
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * generate db transactionManager bean
     *
     * @param termDataSource
     * @return
     */
    @Bean
    @Resource
    public PlatformTransactionManager termDataSourceTxManager(DataSource termDataSource) {
        return new DataSourceTransactionManager(termDataSource);
    }

    @Bean
    @Resource
    public SqlSessionFactory termSqlSessionFactory(DataSource termDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(termDataSource);
        return sqlSessionFactory.getObject();
    }
}
