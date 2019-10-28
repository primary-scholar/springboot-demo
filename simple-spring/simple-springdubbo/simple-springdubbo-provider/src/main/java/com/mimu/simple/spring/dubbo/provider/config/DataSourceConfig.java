package com.mimu.simple.spring.dubbo.provider.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collections;

/**
 * author: mimu
 * date: 2019/8/18
 */
@Slf4j
@Configuration
@PropertySource(value = {"classpath:db-connect.properties"})
@EnableTransactionManagement
public class DataSourceConfig {
    @Value(value = "${db1.write.url}")
    private String db1WriteUrl;
    @Value(value = "${db1.write.username}")
    private String db1WriteUser;
    @Value(value = "${db1.write.password}")
    private String db1WritePassword;
    @Value(value = "${db1.write.shards}")
    private String db1WriteShard;

    @Bean
    public DataSource dataSource() {
        return getDataSource(db1WriteUrl, db1WriteUser, db1WritePassword, true);
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
        if (log.isDebugEnabled()) {
            log.debug("getDataSource info url={}", jdbcUrl);
        }
        return dataSource;
    }
}
