package com.mimu.simple.springboot.sbd2.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * author: mimu
 * date: 2019/7/5
 */
@Slf4j
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

    /**
     * generate datasource properties bean
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "user.datasource")
    public DataSourceProperties userDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "term.datasource")
    public DataSourceProperties termDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * generate datasource bean
     *
     * @return
     */
    @Bean
    public DataSource userDataSource() {
        DataSourceProperties properties = userDataSourceProperties();
        log.info("init fdbDataSource");
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Autowired
    public DataSource termDataSource(DataSourceProperties termDataSourceProperties) {
        //DataSourceProperties db2Properties = sdbDataSourceProperties();
        log.info("init sdbDataSource");
        return termDataSourceProperties.initializeDataSourceBuilder().build();
    }

    /**
     * generate db transactionManager bean
     *
     * @param userDataSource
     * @return
     */
    @Bean
    @Resource
    public PlatformTransactionManager userTxManager(DataSource userDataSource) {
        return new DataSourceTransactionManager(userDataSource);
    }

    @Bean
    @Autowired
    public PlatformTransactionManager termTxManager(DataSource termDataSource) {
        return new DataSourceTransactionManager(termDataSource);
    }

    /**
     * generate jdbcTemplate bean
     *
     * @param userDataSource
     * @return
     */
    @Bean
    @Autowired
    public JdbcTemplate userJdbcTemplate(DataSource userDataSource) {
        return new JdbcTemplate(userDataSource);
    }

    @Bean
    @Autowired
    public JdbcTemplate termJdbcTemplate(DataSource termDataSource) {
        return new JdbcTemplate(termDataSource);
    }

}
