package com.mimu.simple.sa.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * author: mimu
 * date: 2019/5/26
 */
@Configuration
@PropertySource(value = {"classpath:db-connect.properties"})
@EnableTransactionManagement
public class DataSourceConfig {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Value(value = "${db1.write.url}")
    private String db1WriteUrl;
    @Value(value = "${db1.write.username}")
    private String db1WriteUser;
    @Value(value = "${db1.write.password}")
    private String db1WritePassword;
    @Value(value = "${db1.write.shards}")
    private String db1WriteShard;

    @Value(value = "${db1.read.url}")
    private String db1ReadUrl;
    @Value(value = "${db1.read.username}")
    private String db1ReadUser;
    @Value(value = "${db1.read.password}")
    private String db1ReadPassword;
    @Value(value = "${db1.read.shards}")
    private String db1ReadShard;

    @Bean
    public DataSource db1Write() {
        return getDataSource(db1WriteUrl, db1WriteUser, db1WritePassword, true);
    }

    @Bean
    public DataSource db1Read() {
        return getDataSource(db1ReadUrl, db1ReadUser, db1ReadPassword, false);
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource db1Write) {
        return new DataSourceTransactionManager(db1Write);
    }

    /**
     * generate jdbcTemplate bean
     *
     * @param db1Write
     * @return
     */
    @Bean
    @Autowired
    public JdbcTemplate jdbcTemplate(DataSource db1Write) {
        return new JdbcTemplate(db1Write);
    }


    private DataSource getDataSource(String url, String user, String password, boolean useUtf8Mb4) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        String jdbcUrl = String.format("%s"
                , url);
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
        if (logger.isDebugEnabled()) {
            logger.debug("getDataSource info url={}", jdbcUrl);
        }
        return dataSource;
    }

}
