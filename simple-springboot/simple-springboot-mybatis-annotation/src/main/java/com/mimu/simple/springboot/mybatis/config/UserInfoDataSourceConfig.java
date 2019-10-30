package com.mimu.simple.springboot.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * author: mimu
 * date: 2019/8/1
 */

/**
 * 使用了 @MapperScan 就可以不再 各个 *Mapper 的类上 标注 @Mapper 注解了
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.mimu.simple.springboot.mybatis.mapper.user", sqlSessionFactoryRef = "userSqlSessionFactory")
public class UserInfoDataSourceConfig {

    /**
     * generate datasource
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "user.datasource")
    public DataSource userDataSource() {
        log.info("init userDataSource");
        return DruidDataSourceBuilder.create().build();
    }
    /**
     * generate db transactionManager bean
     *
     * @param userDataSource
     * @return
     */
    @Bean
    @Resource
    public PlatformTransactionManager userDataSourceTxManager(DataSource userDataSource) {
        return new DataSourceTransactionManager(userDataSource);
    }

    @Bean
    @Resource
    public SqlSessionFactory userSqlSessionFactory(DataSource userDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(userDataSource);
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sqlSessionFactory.getObject();
    }
}
