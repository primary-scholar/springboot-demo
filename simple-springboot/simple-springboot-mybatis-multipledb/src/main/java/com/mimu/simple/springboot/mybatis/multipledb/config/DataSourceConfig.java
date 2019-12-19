package com.mimu.simple.springboot.mybatis.multipledb.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.mimu.simple.springboot.mybatis.multipledb.enums.DataSourceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 author: mimu
 date: 2019/12/18
 */
@Slf4j
@Configuration
public class DataSourceConfig {

    /**
     * generate user datasource as master
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "user.datasource")
    public DataSource userDataSourceAsMaster() {
        log.info("init userDataSourceAsMaster");
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * generate term datasource as slave
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "term.datasource")
    public DataSource termDataSourceAsSlave() {
        log.info("init termDataSourceAsSlave");
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * generate the routing datasource which has contain two kind of datasource
     * the first type is routing source contains two datasource one master and one slave
     * the second type is general datasource master or slave
     * @param userDataSourceAsMaster
     * @param termDataSourceAsSlave
     * @return
     */
    @Bean
    public DataSource customRoutingDataSource(DataSource userDataSourceAsMaster, DataSource termDataSourceAsSlave) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.master, userDataSourceAsMaster);
        targetDataSource.put(DataSourceType.slave, termDataSourceAsSlave);
        CustomRoutingDataSource customRoutingDataSource = new CustomRoutingDataSource();
        customRoutingDataSource.setDefaultTargetDataSource(termDataSourceAsSlave);
        customRoutingDataSource.setTargetDataSources(targetDataSource);
        return customRoutingDataSource;
    }
}
