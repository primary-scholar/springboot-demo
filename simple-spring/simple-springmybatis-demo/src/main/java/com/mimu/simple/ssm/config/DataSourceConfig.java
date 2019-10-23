package com.mimu.simple.ssm.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Collections;


/**
 * author: mimu
 * date: 2019/10/23
 */
@Configuration
@PropertySource(value = {"classpath:db-connect.properties"})
@EnableTransactionManagement
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value(value = "${user.db.url}")
    private String userDBUrl;
    @Value(value = "${user.db.username}")
    private String userDBUserName;
    @Value(value = "${user.db.password}")
    private String userDBPassword;

    @Bean
    public DataSource userDataSource() {
        return getDataSource(userDBUrl, userDBUserName, userDBPassword, true);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource userDataSource) {
        return new DataSourceTransactionManager(userDataSource);
    }


    private DataSource getDataSource(String url, String user, String password, boolean useUtf8Mb4) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        String jdbcUrl = String.format("%s", url);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(10);
        dataSource.setInitialSize(5);
        dataSource.setMaxWait(60000);
        dataSource.setPoolPreparedStatements(false);
        dataSource.setValidationQuery("select 1");
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeoutMillis(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setBreakAfterAcquireFailure(false);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        if (useUtf8Mb4) {
            dataSource.setConnectionInitSqls(Collections.singletonList("set names utf8mb4;"));
        }
        logger.debug("spring-mybatis getDataSource info url={}", jdbcUrl);
        return dataSource;
    }

}
